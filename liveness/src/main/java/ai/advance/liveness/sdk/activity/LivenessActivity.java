package ai.advance.liveness.sdk.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import ai.advance.common.utils.ScreenUtil;
import ai.advance.liveness.lib.GuardianLivenessDetectionSDK;
import ai.advance.liveness.sdk.R;
import ai.advance.liveness.sdk.fragment.LivenessFragment;

public class LivenessActivity extends AppCompatActivity {

    private LivenessFragment mLivenessFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liveness);
        ScreenUtil.init(this);
        changeAppBrightness(255);
    }

    /**
     * set current activity brightness to max
     * 将当前页面的亮度调节至最大
     */
    public void changeAppBrightness(int brightness) {
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }

    @Override
    protected void onResume() {
        if (GuardianLivenessDetectionSDK.isDeviceSupportLiveness()) {
            mLivenessFragment = LivenessFragment.newInstance();
            if (!mLivenessFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, mLivenessFragment).commitAllowingStateLoss();
            }
        } else {
            new AlertDialog.Builder(this).setMessage(R.string.liveness_device_not_support).setPositiveButton(R.string.liveness_perform, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).create().show();
        }
        super.onResume();

    }

    @Override
    protected void onPause() {
        if (mLivenessFragment != null && mLivenessFragment.isAdded()) {
            mLivenessFragment.release();
            getSupportFragmentManager().beginTransaction().remove(mLivenessFragment).commitAllowingStateLoss();
        }
        super.onPause();
    }

}
