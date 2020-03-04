package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.http.ApiConstant;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_AboutUs extends BaseAct {
    @BindView(R.id.web)
    WebView webView;

    @Override
    public int initLayoutId() {
        return R.layout.act_aboutus;
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
        webView.loadUrl(ApiConstant.ROOT_URL + "public/aboutus" + "?token=" + MyApplication.getLognBean().getMember().getToken());
        setWebView();
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

    @OnClick(R.id.logon_back)
    public void onViewClicked() {
        finish();
    }

    public void setWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
    }
}
