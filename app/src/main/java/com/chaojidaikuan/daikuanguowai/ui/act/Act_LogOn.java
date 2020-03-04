package com.chaojidaikuan.daikuanguowai.ui.act;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.codeLoginBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.getSmsVerifyBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.publicaddressBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act.Act_AgreeWeb;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.JsonDataBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.JsonBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.chaojidaikuan.daikuanguowai.ui.utils.LocationTool;
import com.chaojidaikuan.daikuanguowai.ui.utils.permission.RxPermissions;
import com.chaojidaikuan.daikuanguowai.ui.view.MyTimerText;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页
 */
public class Act_LogOn extends BaseAct implements LocationTool.onBackLoCtion {
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.ll_change)
    LinearLayout llChange;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.tv_change_line)
    TextView tvChangeLine;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.rl_duanxin)
    RelativeLayout rlDuanxin;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_validatecode)
    EditText etValidatecode;
    @BindView(R.id.btn_getcode)
    MyTimerText btnGetcode;
    @BindView(R.id.myTextRadio)
    TextView myTextRadio;
    private LocationTool locationTool;
    private ACache aCache;

    @Override
    public int initLayoutId() {
        return R.layout.act_logon;
    }

    @Override
    public void initView() {
        hideHeader();
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
        locationTool = new LocationTool(Act_LogOn.this, Act_LogOn.this);
        aCache = ACache.get(this);
    }

    @Override
    public void initData() {
        loginStatus = true;
        tvChangeLine.setVisibility(View.VISIBLE);
        tvLine.setVisibility(View.GONE);
        tvChangePassword.setSelected(true);
        tvPassword.setSelected(false);
        llChange.setVisibility(View.VISIBLE);
        etPassword.setVisibility(View.GONE);
        if (aCache.getAsString("phone") != null) {
            etUsername.setText(aCache.getAsString("phone"));
        }
        myTextRadio.setSelected(true);
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    RxPermissions rxPermissions;

    /**
     * 验证码
     */
    public void getCertificateByOldPhone() {
//        if (etUsername.getText().toString().length() != 12) {
//            MyToast.show(Act_LogOn.this, "Nomor ponsel yang anda masukkan tidak benar！");
////            MyToast.show(this, "请输入11位手机号码！");
//            return;
//        }
        loding.show();
        HttpHelper.getSmsVerify(this, etUsername.getText().toString(), "2", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                getSmsVerifyBean getSmsVerifyBean = gson.fromJson(succeed, getSmsVerifyBean.class);
                if (getSmsVerifyBean.getStatus() == 1) {
//                    MyToast.show(Act_LogOn.this, "验证码发送成功！");
                    MyToast.show(Act_LogOn.this, "Kode verifikasi sukses dikirim！");
                    btnGetcode.setBackEndTime(() -> {
                        btnGetcode.setSelected(true);
                    });
                    btnGetcode.setSelected(false);
                    btnGetcode.start();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    @Override
    public void backAdd(String location) {
        if (loginStatus == false) {//账号密码登录
            login(location);
        } else {//验证码登录
            codeLogin(location);
        }
    }

    //返回错误
    @Override
    public void onError() {
        if (aCache.getAsString("cityBean") == null) {
            publicaddress();
            return;
        }
        showPickerView();
    }

    private boolean loginStatus = true;

    @OnClick({R.id.tv_change_password, R.id.tv_password,
            R.id.tv_registe, R.id.btn_login,
            R.id.rl_dongtai, R.id.btn_getcode
            , R.id.tv_error, R.id.userXieyi
            , R.id.myTextRadio, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change_password:
                loginStatus = true;
                tvChangeLine.setVisibility(View.VISIBLE);
                tvLine.setVisibility(View.GONE);
                tvChangePassword.setSelected(true);
                tvPassword.setSelected(false);
                llChange.setVisibility(View.VISIBLE);
                etPassword.setVisibility(View.GONE);
                break;
            case R.id.tv_password:
                loginStatus = false;
                tvChangeLine.setVisibility(View.GONE);
                tvLine.setVisibility(View.VISIBLE);
                tvChangePassword.setSelected(false);
                tvPassword.setSelected(true);
                llChange.setVisibility(View.GONE);
                etPassword.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_registe:
                startActivityForResult(Act_Registe.class, 10);
                break;
            case R.id.btn_login:
                rxPermissions = new RxPermissions(Act_LogOn.this);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                if (loginStatus == false) {//账号密码登录
                                    if (etUsername.getText().toString().length() != 11) {
//            MyToast.show(Act_LogOn.this, "您输入的手机号码位数不对！");
                                        MyToast.show(Act_LogOn.this, "Nomor ponsel yang anda masukkan tidak benar！");
                                        return;
                                    }
                                    if (etPassword.getText().toString().length() == 0) {
//            MyToast.show(Act_LogOn.this, "您输入正确密码");
                                        MyToast.show(Act_LogOn.this, "Kau masukkan kode yang benar");
                                        return;
                                    }
                                    if (myTextRadio.isSelected() == false) {
//            MyToast.show(Act_LogOn.this, "请选择用户协议！");
                                        MyToast.show(Act_LogOn.this, "Silakan pilih protokol pengguna！");
                                        return;
                                    }
                                } else {//验证码登录
                                    if (etUsername.getText().toString().length() != 11) {
//            MyToast.show(Act_LogOn.this, "您输入的手机号码位数不对！");
                                        MyToast.show(Act_LogOn.this, "Nomor ponsel yang anda masukkan tidak benar！");
                                        return;
                                    }
                                    if (etValidatecode.getText().toString().length() != 6) {
//            MyToast.show(Act_LogOn.this, "您输入正确验证码");
                                        MyToast.show(Act_LogOn.this, "Anda memasukkan kode verifikasi yang benar");
                                        return;
                                    }
                                    if (myTextRadio.isSelected() == false) {
//            MyToast.show(Act_LogOn.this, "请选择用户协议！");
                                        MyToast.show(Act_LogOn.this, "Silakan pilih protokol pengguna！");
                                        return;
                                    }
                                }
                                loding.show();
                                locationTool.startLocation();
                            } else {
//                                Toast.makeText(Act_LogOn.this, "请打开相关权限", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Act_LogOn.this, "Buka aksesmu", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_getcode:
                getCertificateByOldPhone();
                break;
            case R.id.tv_error:
                startActivityForResult(Act_FindPassword.class, 10);
                break;
            case R.id.userXieyi:
                startAct(Act_AgreeWeb.class);
                break;
            case R.id.myTextRadio:
                myTextRadio.setSelected(!myTextRadio.isSelected());
                break;
            case R.id.tv_exit:
                finish();
                break;
        }
    }

    /**
     * 验证码登录
     */
    public void codeLogin(String address) {
        loding.show();
        HttpHelper.codeLogin(this, etUsername.getText().toString(), etValidatecode.getText().toString(), address, getSystemModel(), new HttpHelper.HttpUtilsCallBack<String>() {
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
                    aCache.put("lognbean", succeed);
                    aCache.put("phone", etUsername.getText().toString());
                    MyApplication.setLognBean(getSmsVerifyBean.getData());
                    startActClear(Act_Main.class);
                    finish();
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
     * 密码登录
     */
    public void login(String address) {
        Debug.e("---------手机型号====" + getSystemModel());
        HttpHelper.login(this, etUsername.getText().toString(), etPassword.getText().toString(), address, getPackageName(), getSystemModel(), new HttpHelper.HttpUtilsCallBack<String>() {
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
                    aCache.put("lognbean", succeed);
                    aCache.put("phone", etUsername.getText().toString());
                    MyApplication.setLognBean(getSmsVerifyBean.getData());
                    startActClear(Act_Main.class);
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            loginStatus = false;
            tvChangeLine.setVisibility(View.GONE);
            tvLine.setVisibility(View.VISIBLE);
            tvChangePassword.setSelected(false);
            tvPassword.setSelected(true);
            llChange.setVisibility(View.GONE);
            etPassword.setVisibility(View.VISIBLE);
            etUsername.setText(data.getStringExtra("phone"));
            etPassword.setText(data.getStringExtra("psd"));
        }
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
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
                    showPickerView();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    private void showPickerView() {// 弹出选择器
        if (aCache.getAsString("cityBean") == null) {
            return;
        }
        ArrayList<JsonBean> jsonBean = parseData(aCache.getAsString("cityBean"));//用Gson 转成实体
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                String addr = opt1tx + " " + opt2tx + " " + opt3tx;
                if (loginStatus == false) {//账号密码登录
                    login(addr);
                } else {//验证码登录
                    codeLogin(addr);
                }
            }
        })
                .setCancelText("BATAL")//取消按钮文字
                .setSubmitText("OKE")//确认按钮文字
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }
}
