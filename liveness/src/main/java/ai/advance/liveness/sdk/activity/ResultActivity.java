package ai.advance.liveness.sdk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ai.advance.common.IMediaPlayer;
import ai.advance.liveness.lib.LivenessResult;
import ai.advance.liveness.sdk.R;

public class ResultActivity extends AppCompatActivity {
    private ImageView mResultImageView;
    private TextView mResultTextView;
    private TextView mTipTextView;
    private View mTryAgainView;
    private View mRootView;
    private IMediaPlayer mIMediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findViews();
        initViews();
    }

    private void findViews() {
        mResultImageView = findViewById(R.id.detection_result_image_view);
        mResultTextView = findViewById(R.id.detection_result_text_view);
        mTryAgainView = findViewById(R.id.try_again_view);
        mRootView = findViewById(R.id.root_view_activity_result);
        mTipTextView = findViewById(R.id.detection_tip_text_view);
    }

    private void initViews() {
        int lightColor = getResources().getColor(R.color.liveness_color_light);
        int accentColor = getResources().getColor(R.color.liveness_accent);
        mRootView.setBackgroundColor(getResources().getColor(lightColor == accentColor ? R.color.liveness_camera_bg_light : R.color.liveness_camera_bg));
        boolean isSuccess = LivenessResult.isSuccess();
        if (isSuccess) {
            mTipTextView.setText("Liveness score：" + LivenessResult.getLivenessScore());
        } else {
            mTipTextView.setText(LivenessResult.getErrorMsg());
        }
        mResultImageView.setImageResource(isSuccess ? R.drawable.icon_liveness_success : R.drawable.icon_liveness_fail);
        mResultTextView.setText(isSuccess ? R.string.liveness_detection_success : R.string.liveness_detection_fail);

        mTryAgainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        mIMediaPlayer = new IMediaPlayer(this);
        mIMediaPlayer.doPlay(isSuccess ? R.raw.detection_success : R.raw.detection_failed, false, 0);
    }

    @Override
    protected void onDestroy() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.close();
        }
        super.onDestroy();
    }
}
