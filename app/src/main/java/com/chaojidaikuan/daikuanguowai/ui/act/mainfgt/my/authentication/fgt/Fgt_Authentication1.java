package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.publicaddressBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.JsonDataBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userSaveWorkBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userWorkBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.JsonBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.chaojidaikuan.daikuanguowai.ui.utils.LocationTool;
import com.chaojidaikuan.daikuanguowai.ui.utils.permission.RxPermissions;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lykj.aextreme.afinal.common.BaseFragment;
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
import butterknife.Unbinder;

/**
 * 工作信息
 */
public class Fgt_Authentication1 extends BaseFragment {
    @BindView(R.id.spinner1)
    MaterialSpinner spinner1;
    Unbinder unbinder1;
    @BindView(R.id.spinner2)
    MaterialSpinner spinner2;
    @BindView(R.id.spinner3)
    MaterialSpinner spinner3;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.et_income_date)
    EditText etIncomeDate;
    @BindView(R.id.et_telephone)
    EditText etTelephone;
    @BindView(R.id.ll_show)
    LinearLayout llShow;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_authentication1;
    }

    Unbinder unbinder;
    private ACache aCache;

    @Override
    public void initView() {
        hideHeader();
        unbinder = ButterKnife.bind(Fgt_Authentication1.this, v);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    RxPermissions rxPermissions;
    private LocationTool locationTool;
    private boolean gongZuo = true;

    @Override
    public void initData() {
        aCache = ACache.get(getContext());
        type = "Jenis Pekerjaan";
        //工作类型
        spinner1.setItems("Jenis Pekerjaan", "Purna Waktu", "Paruh Waktu", "Wiraswasta", "Tuna Karya", "Pelajar");
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                type = item;
                if (item.equals("Wiraswasta") || item.equals("Tuna Karya") || item.equals("Pelajar")) {//收缩
                    llShow.setVisibility(View.GONE);
                    tvCommit.setSelected(true);
                    gongZuo = true;
                } else {//展示
                    gongZuo = false;
                    llShow.setVisibility(View.VISIBLE);
                    edStatus();
                }
            }
        });
        income = "Kurang dari 1 Juta";
        //每月收入金额
        spinner2.setItems("Kurang dari 1 Juta", "1-3 Juta", "3-5 Juta", "5-10 Juta", "10-20 Juta", "Lebih dari 20 Juuta");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                income = item;
                if (gongZuo) {

                } else {
                    edStatus();
                }
            }
        });
        work_time = "Kurang dari 3 bulan";
        //工作时间长
        spinner3.setItems("Kurang dari 3 bulan", "3 bulan-6 bulan", "6 bulan-1 tahun", "1 tahun-2 tahun", "Lebih dari 2 Tahun");
        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                work_time = item;
                edStatus();
            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = etName.getText().toString();
                edStatus();
            }
        });
        etIncomeDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                income_date = etIncomeDate.getText().toString();
                edStatus();
            }
        });
        etTelephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                telephone = etTelephone.getText().toString();
                edStatus();
            }
        });
        tvAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                address = etTelephone.getText().toString();
                Debug.e("---------address===" + address);
                edStatus();
            }
        });
        if (MyApplication.lognStatus) {
            userWork();
        }
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    /**
     * 获取工作信息
     */
    public void userWork() {
        HttpHelper.userWork(getContext(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userWorkBean loangetBean = gson.fromJson(succeed, userWorkBean.class);
                if (loangetBean.getStatus() == 1) {
                    if (loangetBean.getData().getData() == null) {//未认证工作信息
                    } else {
                        tvCommit.setSelected(true);
                        etName.setFocusable(false);
                        etName.setText(loangetBean.getData().getData().getName());
                        tvAddress.setText(loangetBean.getData().getData().getAddress());
                        etIncomeDate.setFocusable(false);
                        etIncomeDate.setText(loangetBean.getData().getData().getIncome_date());
                        etTelephone.setFocusable(false);
                        etTelephone.setText(loangetBean.getData().getData().getTelephone());
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

    String type = "",
            income = "",
            work_time = "",
            name = "",
            address = "",
            income_date = "",
            telephone = "";

    @OnClick({R.id.tv_commit, R.id.im_addr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
//                if (userWork) {//认证后的=》下一步
//                    Debug.e("--------下一步");
//                } else {//未认证
                if (gongZuo) {
                    userSaveWork1();
                } else {
                    userSaveWork();
                }
//                }
                break;
            case R.id.im_addr:
                if (aCache.getAsString("cityBean") == null) {
                    publicaddress();
                    return;
                }
                showPickerView();
                break;
        }
    }

    /**
     * 状态显示
     */
    public void edStatus() {
        tvCommit.setSelected(false);
        if (TextUtils.isEmpty(type)) {
            return;
        }
        if (TextUtils.isEmpty(income)) {
            return;
        }
        if (TextUtils.isEmpty(work_time)) {
            return;
        }
        if (TextUtils.isEmpty(name)) {
            return;
        }
//        if (TextUtils.isEmpty(address)) {
//            return;
//        }
        if (TextUtils.isEmpty(income_date)) {
            return;
        }
        if (TextUtils.isEmpty(telephone)) {
            return;
        }
        tvCommit.setSelected(true);
    }

    /**
     * 保存工作信息
     */
    public void userSaveWork() {
        if (!tvCommit.isSelected()) {
            MyToast.show(getContext(), "Anda juga memiliki argumen yang tidak dipilih atau dimasukkan");
            return;
        }
        address = tvAddress.getText().toString();
        loding.show();
        HttpHelper.userSaveWork(getContext(), type, income, work_time, name, address, income_date, telephone, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userSaveWorkBean loangetBean = gson.fromJson(succeed, userSaveWorkBean.class);
                if (loangetBean.getStatus() == 1) {
                    MyToast.show(getContext(), "Kirim berhasil！");
                    getActivity().setResult(10);
                    getActivity().finish();
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
     * 保存工作信息
     */
    public void userSaveWork1() {
        if (!tvCommit.isSelected()) {
            MyToast.show(getContext(), "Anda juga memiliki argumen yang tidak dipilih atau dimasukkan");
            return;
        }
        loding.show();
        HttpHelper.userSaveWork(getContext(), type, income, "", "", "", "", "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userSaveWorkBean loangetBean = gson.fromJson(succeed, userSaveWorkBean.class);
                if (loangetBean.getStatus() == 1) {
                    MyToast.show(getContext(), "Kirim berhasil！");
                    getActivity().setResult(10);
                    getActivity().finish();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
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
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
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
                address = opt1tx + " " + opt2tx + " " + opt3tx;
                tvAddress.setText(address);
                edStatus();
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
