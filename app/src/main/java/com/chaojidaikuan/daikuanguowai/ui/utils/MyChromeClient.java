package com.chaojidaikuan.daikuanguowai.ui.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.io.File;

public class MyChromeClient extends WebChromeClient {
    public ValueCallback<Uri> UploadMsg;
    public ValueCallback<Uri[]> UploadMsg2;
    private Activity activity;

    public static final int FILECHOOSER_RESULTCODE = 5173;

    public static String mCameraFilePath = "";

    @SuppressWarnings("deprecation")
    public MyChromeClient(Activity cordova) {

        this.activity = cordova;

    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {

        super.onProgressChanged(view, newProgress);

    }



    // <input type="file" name="fileField" id="fileField" />

    // Android > 4.1.1

    @Override
    public boolean onShowFileChooser(WebView webView,
                                     ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {
        // TODO 自动生成的方法存根
        UploadMsg2 = filePathCallback;
        this.activity.startActivityForResult(createDefaultOpenableIntent(),

                this.FILECHOOSER_RESULTCODE);
        return false;
    }

    @SuppressWarnings("static-access")
    public void openFileChooser(ValueCallback<Uri> uploadMsg,

                                String acceptType, String capture) {

        UploadMsg = uploadMsg;

        this.activity.startActivityForResult(createDefaultOpenableIntent(),

                this.FILECHOOSER_RESULTCODE);

    }

    // 3.0 +

    @SuppressWarnings("static-access")
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

        UploadMsg = uploadMsg;

        this.activity.startActivityForResult(createDefaultOpenableIntent(),

                this.FILECHOOSER_RESULTCODE);

    }

    // Android < 3.0

    @SuppressWarnings("static-access")
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {

        UploadMsg = uploadMsg;

        this.activity.startActivityForResult(createDefaultOpenableIntent(),

                this.FILECHOOSER_RESULTCODE);

    }

    private Intent createDefaultOpenableIntent() {

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);

        i.addCategory(Intent.CATEGORY_OPENABLE);

        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,

                "image/*");

        Intent chooser = createChooserIntent(createCameraIntent()/*
         *
         * ,
         *
         * createCamcorderIntent
         *
         * (),
         *
         * createSoundRecorderIntent
         *
         * ()
         */);

        chooser.putExtra(Intent.EXTRA_INTENT, i);

        return chooser;

    }

    private Intent createChooserIntent(Intent... intents) {

        Intent chooser = new Intent(Intent.ACTION_CHOOSER);

        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents);

        chooser.putExtra(Intent.EXTRA_TITLE, "选择图片");

        return chooser;

    }

    @SuppressWarnings("static-access")
    private Intent createCameraIntent() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File externalDataDir = Environment

                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        File cameraDataDir = new File(externalDataDir.getAbsolutePath()

                + File.separator + "515aaa");

        cameraDataDir.mkdirs();

        String mCameraFilePath = cameraDataDir.getAbsolutePath()

                + File.separator + System.currentTimeMillis() + ".jpg";

        this.mCameraFilePath = mCameraFilePath;

        cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,

                Uri.fromFile(new File(mCameraFilePath)));

        return cameraIntent;

    }

    /*
     *
     * private Intent createCamcorderIntent() { return new
     *
     * Intent(MediaStore.ACTION_VIDEO_CAPTURE); }
     *
     *
     *
     * private Intent createSoundRecorderIntent() { return new
     *
     * Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION); }
     */

    public static Uri getImageContentUri(Context context, File imageFile) {

        String filePath = imageFile.getAbsolutePath();

        Cursor cursor = context.getContentResolver().query(

                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,

                new String[] { MediaStore.Images.Media._ID },

                MediaStore.Images.Media.DATA + "=? ",

                new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {

            int id = cursor.getInt(cursor

                    .getColumnIndex(MediaStore.MediaColumns._ID));

            Uri baseUri = Uri.parse("content://media/external/images/media");

            return Uri.withAppendedPath(baseUri, "" + id);

        } else {

            if (imageFile.exists()) {

                ContentValues values = new ContentValues();

                values.put(MediaStore.Images.Media.DATA, filePath);

                return context.getContentResolver().insert(

                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            } else {

                return null;

            }

        }

    }

}