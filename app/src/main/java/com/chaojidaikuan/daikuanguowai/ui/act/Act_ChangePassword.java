package com.chaojidaikuan.daikuanguowai.ui.act;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.userModify_passwordBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.MyToast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 修改密码
 */
public class Act_ChangePassword extends BaseAct {
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_sure_password)
    EditText etSurePassword;

    @Override
    public int initLayoutId() {
        return R.layout.act_changepassword;
    }

    @Override
    public void initView() {
        hideHeader();
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    @Override
    public void initData() {

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

    @OnClick({R.id.tv_exit, R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_exit:
                finish();
                break;
            case R.id.btn_change:
                userModify_password();
                break;
        }
    }

    /**
     * 修改密码
     */
    public void userModify_password() {
        if (etPassword.getText().toString().length() == 0) {
            MyToast.show(Act_ChangePassword.this, "Kau masukkan kode yang benar");
            return;
        }
        loding.show();
        HttpHelper.userModify_password(this, etPassword.getText().toString(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userModify_passwordBean getSmsVerifyBean = gson.fromJson(succeed, userModify_passwordBean.class);
                if (getSmsVerifyBean.getStatus() == 1) {
                    MyToast.show(Act_ChangePassword.this, "Pengubahan sandi berhasil.");
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
}
