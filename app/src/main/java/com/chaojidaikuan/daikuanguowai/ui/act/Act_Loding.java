package com.chaojidaikuan.daikuanguowai.ui.act;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.checkforupdateBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.codeLoginBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.publicaddressBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.JsonDataBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.chaojidaikuan.daikuanguowai.ui.utils.DeviceIdUtil;
import com.chaojidaikuan.daikuanguowai.ui.utils.I18NUtils;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 加载页
 */
public class Act_Loding extends BaseAct {
    @Override
    public int initLayoutId() {
        return R.layout.act_loding;
    }

    private ACache aCache;

    @Override
    public void initView() {
        aCache = ACache.get(this);
        updateHandler.sendEmptyMessageDelayed(14, 2000);
    }

    int i = 0;
    private Handler updateHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 14) {
                checkforupdate();
            }
        }
    };

    @Override
    public void initData() {
        hideHeader();
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
//        toSetLanguage(1);
        userstatistics();
        //获取地址信息
        publicaddress();
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    /**
     * 更新
     */
    public void checkforupdate() {
        HttpHelper.checkforupdate(this, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                checkforupdateBean getSmsVerifyBean = gson.fromJson(succeed, checkforupdateBean.class);
                if (getSmsVerifyBean.getStatus() == 1) {
                    Double versionCode = Double.valueOf(getVersionName());
                    if (versionCode < Double.valueOf(getSmsVerifyBean.getData().getData().getCode())) {
                        upData(getSmsVerifyBean.getData().getData().getApp_link());
                    } else {
//                        if (aCache.getAsString("lognbean") != null && !aCache.getAsString("lognbean").equals("")) {
//                            codeLoginBean codeLoginBean = gson.fromJson(aCache.getAsString("lognbean"), codeLoginBean.class);
//                            MyApplication.setLognBean(codeLoginBean.getData());
                        startActClear(Act_Main.class);
//                            finish();
//                        } else {
//                        startAct(Act_LogOn.class);
                        finish();
//                        }
                    }
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    private long getVersionName() {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }

    public void upData(final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更新提示");
        builder.setMessage("退出");
        builder.setNegativeButton("退出软件", (dialogInterface, i) -> finish());
        builder.setPositiveButton("开始更新", (dialogInterface, i) -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            startActivity(intent);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void toSetLanguage(int type) {
        boolean sameLanguage = I18NUtils.isSameLanguage(this, type);
        if (!sameLanguage) {
            I18NUtils.setLocale(this, type);
            // 前面取系统语言时判断spType=0时取第一值，所以设置完语言后缓存type
            I18NUtils.putLanguageType(this, type);
            I18NUtils.toRestartMainActvity(this);
        } else {
            // 缓存用户此次选择的类型，可能出现type不同而locale一样的情况（如：系统默认泰语type = 0，而我选择的也是泰语type = 3）
            I18NUtils.putLanguageType(this, type);
        }
    }

    /**
     * 统计app下载
     */
    public void userstatistics() {
        String deviceId = DeviceIdUtil.getDeviceId(this);
        HttpHelper.userstatistics(this, deviceId, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                codeLoginBean getSmsVerifyBean = gson.fromJson(succeed, codeLoginBean.class);
                if (getSmsVerifyBean.getStatus() == 1) {
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    /**
     * 地址选择
     */
    public void publicaddress() {
        HttpHelper.publicaddress(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                publicaddressBean getSmsVerifyBean = gson.fromJson(succeed, publicaddressBean.class);
                if (getSmsVerifyBean.getStatus() == 1) {
                    JsonDataBean jsonDataBean = new JsonDataBean();
                    List<JsonDataBean.DataBean> shengfen = new ArrayList<>();
                    for (int i = 0; i < getSmsVerifyBean.getData().size(); i++) {
                        JsonDataBean.DataBean dataBean = new JsonDataBean.DataBean();
                        List<JsonDataBean.DataBean.CityBean> City = new ArrayList<>();
                        for (int j = 0; j < getSmsVerifyBean.getData().get(i).getRegencies().size(); j++) {
                            JsonDataBean.DataBean.CityBean bean = new JsonDataBean.DataBean.CityBean();
                            List<String> area = new ArrayList<>();
                            for (int f = 0; f < getSmsVerifyBean.getData().get(i).getRegencies().get(j).getDistricts().size(); f++) {
                                area.add(getSmsVerifyBean.getData().get(i).getRegencies().get(j).getDistricts().get(f).getName());
                            }
                            bean.setName(getSmsVerifyBean.getData().get(i).getRegencies().get(j).getName());
                            bean.setArea(area);
                            City.add(bean);
                        }
                        dataBean.setName(getSmsVerifyBean.getData().get(i).getName());
                        dataBean.setCity(City);
                        shengfen.add(dataBean);
                    }
                    jsonDataBean.setData(shengfen);
                    String datas = gson.toJson(jsonDataBean);
                    JSONObject jsonObject = null;
                    String data = "";
                    try {
                        jsonObject = new JSONObject(datas);
                        data = jsonObject.getString("data");
                        aCache.put("cityBean", data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }
}
