package com.chaojidaikuan.daikuanguowai.commt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.chaojidaikuan.daikuanguowai.BuildConfig;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.codeLoginBean;
import com.chaojidaikuan.daikuanguowai.ui.utils.I18NUtils;
import com.lykj.aextreme.afinal.common.BaseApplication;
import com.tencent.bugly.crashreport.CrashReport;

import ai.advance.liveness.lib.GuardianLivenessDetectionSDK;
import ai.advance.liveness.lib.Market;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class MyApplication extends BaseApplication {
    public static boolean lognStatus = true;//false判断直接认证true 判断数据恢复
    public static String oderid = "";
    public static String oderno = "";
    private static codeLoginBean.DataBean lognBean;

    public static codeLoginBean.DataBean getLognBean() {
        return lognBean;
    }

    public static void setLognBean(codeLoginBean.DataBean lognBean) {
        MyApplication.lognBean = lognBean;
    }

    private static MyApplication app;
    public static boolean onBackStatus = false;

    public static MyApplication getApp() {
        return app;
    }

    /**
     * 获取Application的Context
     **/
    public static Context getAppContext() {
        if (app == null)
            return null;
        return app.getApplicationContext();
    }

    private static final String AF_DEV_KEY = "dwn9ukzAttETzmyDWfHXbm";

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //  设置本地化语言
        I18NUtils.setLocale(this);
        //统计数据
        String appToken = "x34bvgkv9uyo";
        String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        config.setLogLevel(LogLevel.VERBOSE);
        Adjust.onCreate(config);
        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
        //面部识别初始化
        GuardianLivenessDetectionSDK.init(this, BuildConfig.LivenessAccessKey, BuildConfig.livenessSecretKey, Market.Indonesia);
       //bug测试收集工具
        CrashReport.initCrashReport(getApplicationContext(), "f9104212cf", false);
    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    }
}