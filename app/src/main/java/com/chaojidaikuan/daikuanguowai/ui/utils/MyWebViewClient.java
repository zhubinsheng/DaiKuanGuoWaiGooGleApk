package com.chaojidaikuan.daikuanguowai.ui.utils;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MyWebViewClient extends BridgeWebViewClient {
    public BridgeWebView webView;
    private Activity activity;
    private backWebviewStatus backWebviewStatus;

    public MyWebViewClient(BridgeWebView webView1, Activity activity1, backWebviewStatus backWebviewStatus1) {
        super(webView1);
        backWebviewStatus = backWebviewStatus1;
        webView = webView1;
        activity = activity1;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("http:") || url.startsWith("https:")) {
            view.loadUrl(url);
            return false;
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
        }
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (!isLoadError) {
            backWebviewStatus.LoadSuccess();
        }

    }

    private boolean isLoadError;

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        isLoadError = true;
        backWebviewStatus.onWebErro();
    }

    // 增加shouldOverrideUrlLoading在api》=24时
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String url = request.getUrl().toString();
            try {
                url = URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            if (url.startsWith(YY_RETURN_DATA)) { // 如果是返回数据
                webView.handlerReturnData(url);
                return true;
            } else if (url.startsWith(YY_OVERRIDE_SCHEMA)) { //
                webView.flushMessageQueue();
                return true;
            } else {
                return this.onCustomShouldOverrideUrlLoading(url) ? true : super.shouldOverrideUrlLoading(view, request);
            }
        } else {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    final static String YY_OVERRIDE_SCHEMA = "yy://";
    final static String YY_RETURN_DATA = YY_OVERRIDE_SCHEMA + "return/";//格式为   yy://return/{function}/returncontent

    public interface backWebviewStatus {
        void onWebErro();

        void LoadSuccess();
    }
}