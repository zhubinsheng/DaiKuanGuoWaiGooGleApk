package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.MyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.http.ApiConstant;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.MyToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 借款协议
 */
public class Act_XieYI extends BaseAct {
    @Override
    public int initLayoutId() {
        return R.layout.act_xieyi;
    }

    private WebView webView;

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
        webView = (WebView) findViewById(R.id.web);
        setWebView();
        webView.setWebChromeClient(new WebChromeClient());
        Map<String,String> map=new HashMap<>();
        map.put("token", MyApplication.getLognBean().getMember().getToken());
        webView.loadUrl(ApiConstant.ROOT_URL + "user/contract",map);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
//        userContract();
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
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
    }


    /**
     * 获取我的借款合同
     */
    public void userContract() {
        HttpHelper.userContract(this, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                MyLoanBean entity = gson.fromJson(succeed, MyLoanBean.class);
                if (entity.getStatus() == 1) {

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
