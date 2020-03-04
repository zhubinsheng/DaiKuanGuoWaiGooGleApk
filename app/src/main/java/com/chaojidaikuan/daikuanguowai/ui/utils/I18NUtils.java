package com.chaojidaikuan.daikuanguowai.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.webkit.WebView;

import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.Act_Loding;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.Utils;

import java.util.Locale;

/**
 * Created by Fitem on 2017/12/8.
 */
public class I18NUtils {
    private static final String TAG = "I18NUtils";
    private static Locale thLocale = new Locale("th");

    /**
     * 设置本地化语言
     *
     * @param context
     * @param type
     */
    public static void setLocale(Context context, int type) {
        // 解决webview所在的activity语言没有切换问题
        new WebView(context).destroy();
        // 切换语言
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.locale = getLocaleByType(type);
        resources.updateConfiguration(config, dm);
    }

    /**
     * 根据type获取locale
     *
     * @param type
     * @return
     */
    private static Locale getLocaleByType(int type) {
        Locale locale;
        Debug.e("-------------应用用户选择语言==" + type);
        // 应用用户选择语言
        switch (type) {
            case 0:
                //由于API仅支持7.0，需要判断，否则程序会crash(解决7.0以上系统不能跟随系统语言问题)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    LocaleList localeList = LocaleList.getDefault();
                    int spType = getLanguageType(MyApplication.getApp());
                    // 如果app已选择不跟随系统语言，则取第二个数据为系统默认语言
                    if (spType != 0 && localeList.size() > 1) {
                        locale = localeList.get(1);
                    } else {
                        locale = localeList.get(0);
                    }
                } else {
                    locale = Locale.getDefault();
                }
                break;
            case 1:
                locale = new Locale("id");
                break;
            case 2:
                locale = Locale.CHINESE;
                break;
            default:
                locale = thLocale;
                break;
        }
        return locale;
    }

    /**
     * 根据sp数据设置本地化语言
     *
     * @param context
     */
    public static void setLocale(Context context) {
        int type = getLanguageType(context);
        setLocale(context, type);
    }

    /**
     * 判断是否是相同语言
     *
     * @param context
     * @return
     */
    public static boolean isSameLanguage(Context context) {
        int type = getLanguageType(context);
        return isSameLanguage(context, type);
    }

    /**
     * 判断是否是相同语言
     *
     * @param context
     * @param type
     * @return
     */
    public static boolean isSameLanguage(Context context, int type) {
        Locale locale = getLocaleByType(type);
        Locale appLocale = context.getResources().getConfiguration().locale;
        boolean equals = appLocale.equals(locale);
        return equals;
    }

    /**
     * sp存储本地语言类型
     *
     * @param context
     * @param type
     */
    public static void putLanguageType(Context context, int type) {
        SharedPreferences sp = context.getSharedPreferences(AppConstants.I18N, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(AppConstants.LOCALE_LANGUAGE, type);
        edit.commit();
    }

    /**
     * sp获取本地存储语言类型
     *
     * @param context
     * @return
     */
    private static int getLanguageType(Context context) {
        SharedPreferences sp = context.getSharedPreferences(AppConstants.I18N, Context.MODE_PRIVATE);
        int type = sp.getInt(AppConstants.LOCALE_LANGUAGE, 0);
        return type;
    }

    /**
     * 跳转主页
     *
     * @param activity
     */
    public static void toRestartMainActvity(Activity activity) {
        if (Utils.isFastClick() == false) {//防点击过快
        } else {
            Intent intent = new Intent(activity, Act_Loding.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
            activity.finish();
        }
        // 杀掉进程，如果是跨进程则杀掉当前进程
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }
}
