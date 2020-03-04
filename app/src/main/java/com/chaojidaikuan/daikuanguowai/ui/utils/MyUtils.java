package com.chaojidaikuan.daikuanguowai.ui.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.chaojidaikuan.daikuanguowai.ui.utils.permission.RxPermissions;
import com.chaojidaikuan.daikuanguowai.ui.utils.permission.SchedulerTransformer;
import com.lykj.aextreme.afinal.utils.MyToast;

import static com.mob.MobSDK.getContext;

public class MyUtils {
    /**
     * 高度转换
     *
     * @param dp
     * @return
     */
    public static int dip2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }
    /**
     * 获取版本号名称
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 图片资源内存回收
     *
     * @param imageView
     */
    public static void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }
    /**
     * 拨打电话加权限申请
     * @param phone
     */
    public static void callPhone(String phone, Activity act) {
        new RxPermissions(act)
                .request(Manifest.permission.CALL_PHONE)
                .compose(new SchedulerTransformer<Boolean>())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        call(phone,act);
                    } else {
                        MyToast.show(act, "请打开读拨打电话权限");
                    }
                });
    }
    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public static void call(String phoneNum, Activity act) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        act.startActivity(intent);
    }

    /**
     * URI得到路劲
     *
     * @param uri
     * @param act
     * @return
     */
    public static String getPath(Uri uri, Activity act) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = act.managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
