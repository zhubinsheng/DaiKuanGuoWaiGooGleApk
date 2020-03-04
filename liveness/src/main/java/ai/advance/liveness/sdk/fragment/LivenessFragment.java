package ai.advance.liveness.sdk.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import ai.advance.common.IMediaPlayer;
import ai.advance.liveness.lib.Detector;
import ai.advance.liveness.lib.LivenessResult;
import ai.advance.liveness.lib.LivenessView;
import ai.advance.liveness.lib.http.entity.ResultEntity;
import ai.advance.liveness.lib.impl.LivenessCallback;
import ai.advance.liveness.lib.impl.LivenessGetFaceDataCallback;
import ai.advance.liveness.sdk.R;

import static ai.advance.liveness.lib.Detector.DetectionType.POS_YAW;

/**
 * fragment of liveness detection
 * 活体检测功能页面
 *
 * @author https://www.advance.ai
 */
public class LivenessFragment extends Fragment implements Detector.DetectorInitCallback, LivenessCallback {
    /**
     * the array of tip imageView animationDrawable
     * 动作提示 imageView 的图像集合
     */
    private SparseArray<AnimationDrawable> mDrawableCache;
    /**
     * the circle mask view above livenessView
     * 蒙版控件
     */
    protected ImageView mMaskImageView;
    /**
     * liveness function view
     * 活体检测功能控件
     */
    private LivenessView mLivenessView;
    /**
     * bottom anim tip imageView
     * 底部提示动画控件
     */
    private ImageView mTipImageView;
    /**
     * bottom tip textView
     * 底部提示文本控件
     */
    private TextView mTipTextView;
    /**
     * the countdown timer view
     * 倒计时控件
     */
    private TextView mTimerView;
    /**
     * open/close sounds checkbox
     * 打开/关闭声音的单选框
     */
    private CheckBox mVoiceCheckBox;
    /**
     * the loading dialog after all action success
     * 全部动作成功后的加载框
     */
    private View mProgressLayout;
    /**
     * auth loading dialog
     * 授权过程的加载框
     */
    ProgressDialog mInitProgressDialog;

    public static LivenessFragment newInstance() {
        return new LivenessFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liveness, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews();
        initData();
    }

    /**
     * init fields
     * 初始化变量
     */
    private void initData() {
        mDrawableCache = new SparseArray<>();
        // start liveness detection check 启动活体检测
        mLivenessView.startDetection(this);
    }

    /**
     * init views
     * 初始化控件
     */
    protected void findViews() {
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            mMaskImageView = activity.findViewById(R.id.mask_view);
            mLivenessView = activity.findViewById(R.id.liveness_view);
            mTipImageView = activity.findViewById(R.id.tip_image_view);
            mTipTextView = activity.findViewById(R.id.tip_text_view);
            mTimerView = activity.findViewById(R.id.timer_text_view_camera_activity);
            mProgressLayout = activity.findViewById(R.id.progress_layout);
            mVoiceCheckBox = activity.findViewById(R.id.voice_check_box);
            View mBackView = activity.findViewById(R.id.back_view_camera_activity);
            mBackView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
            mVoiceCheckBox.setChecked(IMediaPlayer.isPlayEnable());
            mVoiceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mLivenessView.setSoundPlayEnable(isChecked);
                    if (isChecked) {
                        playSound();
                    }
                }
            });
        }
    }

    /**
     * play sound
     * 播放语音
     */
    private void playSound() {
        if (mVoiceCheckBox.getVisibility() != View.VISIBLE) {
            mVoiceCheckBox.setVisibility(View.VISIBLE);
        }
        int resID = -1;
        Detector.DetectionType detectionType = mLivenessView.getCurrentDetectionType();
        if (detectionType != null) {
            switch (detectionType) {
                case POS_YAW:
                    resID = R.raw.action_turn_head;
                    break;
                case MOUTH:
                    resID = R.raw.action_open_mouth;
                    break;
                case BLINK:
                    resID = R.raw.action_blink;
                    break;
            }
        }
        mLivenessView.playSound(resID, true, 1500);
    }

    /**
     * update tip text
     * 更新提示语文案
     *
     * @param strResId resId 资源id
     */
    private void changeTipTextView(int strResId) {
        mTipTextView.setText(strResId);
    }

    /**
     * update tip textView text
     * 更新提示文本的文案
     *
     * @param warnCode the status of current frame 当前的状态
     */
    private void updateTipUIView(Detector.WarnCode warnCode) {
        if (mLivenessView.isVertical()) {//phone not vertical
            if (warnCode != null) {
                switch (warnCode) {
                    case FACEMISSING:
                        changeTipTextView(R.string.liveness_no_people_face);
                        break;
                    case FACESMALL:
                        changeTipTextView(R.string.liveness_tip_move_closer);
                        break;
                    case FACELARGE:
                        changeTipTextView(R.string.liveness_tip_move_furthre);
                        break;
                    case FACENOTCENTER:
                        changeTipTextView(R.string.liveness_move_face_center);
                        break;
                    case FACENOTFRONTAL:
                        changeTipTextView(R.string.liveness_frontal);
                        break;
                    case FACENOTSTILL:
                    case FACECAPTURE:
                        changeTipTextView(R.string.liveness_still);
                        break;
                    case FACEINACTION:
                        showActionTipUIView();
                        break;
                }
            }
        } else {
            changeTipTextView(R.string.liveness_hold_phone_vertical);
        }
    }

    /**
     * show current action tips
     * 显示当前动作的动画提示
     */
    private void showActionTipUIView() {
        Detector.DetectionType currentDetectionType = mLivenessView.getCurrentDetectionType();
        if (currentDetectionType != null) {
            int detectionNameId = 0;
            switch (currentDetectionType) {
                case POS_YAW:
                    detectionNameId = R.string.liveness_pos_raw;
                    break;
                case MOUTH:
                    detectionNameId = R.string.liveness_mouse;
                    break;
                case BLINK:
                    detectionNameId = R.string.liveness_blink;
                    break;
            }
            changeTipTextView(detectionNameId);
            AnimationDrawable anim = getDrawRes(currentDetectionType);
            mTipImageView.setImageDrawable(anim);
            anim.start();
        }
    }

    @Override
    public void onDetach() {
        release();
        super.onDetach();
    }

    /**
     * called by when detection auth start
     * 活体检测授权开始时会执行该方法
     */
    @Override
    public void onDetectorInitStart() {
        if (mInitProgressDialog != null) {
            mInitProgressDialog.dismiss();
        }
        mInitProgressDialog = new ProgressDialog(getContext());
        mInitProgressDialog.setMessage(getString(R.string.liveness_auth_check));
        mInitProgressDialog.setCanceledOnTouchOutside(false);
        mInitProgressDialog.show();
    }

    /**
     * release the fragment resource
     * 释放 fragment 资源
     */
    public void release() {
        if (mInitProgressDialog != null) {
            mInitProgressDialog.dismiss();
        }
        mLivenessView.destory();
    }

    /**
     * called by when detection auth complete
     * 活体检测授权完成后会执行该方法
     *
     * @param isValid   whether the auth is success 活体检测是否成功
     * @param errorCode the error code 错误码
     * @param message   the error message 错误信息
     */
    @Override
    public void onDetectorInitComplete(final boolean isValid, final String errorCode,
                                       final String message) {
        if (mInitProgressDialog != null) {
            mInitProgressDialog.dismiss();
        }
        if (isValid) {
            updateTipUIView(null);
        } else {
            final String errorMessage;
            if (LivenessView.NO_RESPONSE.equals(errorCode)) {
                errorMessage = getString(R.string.liveness_failed_reason_auth_failed);
            } else {
                errorMessage = message;
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                new AlertDialog.Builder(activity).setMessage(errorMessage).setPositiveButton(R.string.liveness_perform, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LivenessResult.setErrorMsg(errorMessage);
                        FragmentActivity activity = getActivity();
                        dialog.dismiss();
                        if (activity != null) {
                            activity.setResult(Activity.RESULT_OK);
                            activity.finish();
                        }
                    }
                }).create().show();
            }
        }
    }

    /**
     * Get the prompt picture/animation according to the action type
     * 根据动作类型获取动画资源
     *
     * @param detectionType Action type 动作类型
     * @return Prompt picture/animation
     */
    public AnimationDrawable getDrawRes(Detector.DetectionType detectionType) {
        int resID = -1;
        if (detectionType != null) {
            switch (detectionType) {
                case POS_YAW:
                    resID = R.drawable.anim_frame_turn_head;
                    break;
                case MOUTH:
                    resID = R.drawable.anim_frame_open_mouse;
                    break;
                case BLINK:
                    resID = R.drawable.anim_frame_blink;
                    break;
            }
        }
        AnimationDrawable cachedDrawAble = mDrawableCache.get(resID);
        if (cachedDrawAble == null) {
            AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(resID);
            mDrawableCache.put(resID, (drawable));
            return drawable;
        } else {
            return cachedDrawAble;
        }
    }

    /**
     * called by first action start or after an action finish
     * 当准备阶段完成时，以及每个动作完成后，会执行该方法
     */
    @Override
    public void onDetectionActionChanged() {
        playSound();
        showActionTipUIView();
        mTimerView.setBackgroundResource(R.drawable.liveness_shape_right_timer);
    }

    /**
     * called by local liveness detection success
     * 活体检测成功时会执行该方法
     */
    @Override
    public void onDetectionSuccess() {
        mLivenessView.getLivenessData(new LivenessGetFaceDataCallback() {

            @Override
            public void onGetFaceDataStart() {
                mProgressLayout.setVisibility(View.VISIBLE);
                mTimerView.setVisibility(View.GONE);
                mLivenessView.setVisibility(View.GONE);
                mVoiceCheckBox.setVisibility(View.GONE);
                mTipImageView.setVisibility(View.GONE);
                mTipTextView.setVisibility(View.GONE);
                mMaskImageView.setVisibility(View.GONE);
            }

            @Override
            public void onGetFaceDataSuccess(ResultEntity entity, String livenessId) {
                // liveness detection success
                setResultData();
            }

            @Override
            public void onGetFaceDataFailed(ResultEntity entity) {
                if (!entity.success && LivenessView.NO_RESPONSE.equals(entity.code)) {
                    LivenessResult.setErrorMsg(getString(R.string.liveness_failed_reason_bad_network));
                }
                setResultData();
            }
        });
    }

    private void setResultData() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.setResult(Activity.RESULT_OK);
            activity.finish();
        }
    }

    /**
     * called by current frame is warn or become normal,is necessary to update tip UI
     * 当前帧的状态发生异常或者从异常状态变为正常的时候，需要更新 UI 上的提示语
     *
     * @param warnCode status of current frame 本帧的状态
     */
    @Override
    public void onDetectionFrameStateChanged(Detector.WarnCode warnCode) {
        if (isAdded()) {
            updateTipUIView(warnCode);
        }
    }

    /**
     * called by Remaining time changed of current action,is necessary to update countdown timer view
     * 当前动作剩余时间变化,需要更新倒计时控件上的时间
     *
     * @param remainingTimeMills remaining time of current action 毫秒单位的剩余时间
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onActionRemainingTimeChanged(long remainingTimeMills) {
        if (isAdded()) {
            final int mills = (int) (remainingTimeMills / 1000);
            mTimerView.setText(mills + "s");
        }
    }

    /**
     * called by detection failed
     * 活体检测失败时的回调
     *
     * @param failedType    Type of failures 失败的类型
     * @param detectionType Type of action 失败的原因
     */
    @Override
    public void onDetectionFailed(Detector.DetectionFailedType failedType, Detector.DetectionType detectionType) {
        if (isAdded()) {
            switch (failedType) {
                case WEAKLIGHT:
                    changeTipTextView(R.string.liveness_weak_light);
                    break;
                case STRONGLIGHT:
                    changeTipTextView(R.string.liveness_too_light);
                    break;
                default:
                    String errorMsg = null;
                    switch (failedType) {
                        case FACEMISSING:
                            switch (detectionType) {
                                case MOUTH:
                                case BLINK:
                                    errorMsg = getString(R.string.liveness_failed_reason_facemissing_blink_mouth);
                                    break;
                                case POS_YAW:
                                    errorMsg = getString(R.string.liveness_failed_reason_facemissing_pos_yaw);
                                    break;
                            }
                            break;
                        case TIMEOUT:
                            errorMsg = getString(R.string.liveness_failed_reason_timeout);
                            break;
                        case MULTIPLEFACE:
                            errorMsg = getString(R.string.liveness_failed_reason_multipleface);
                            break;
                        case MUCHMOTION:
                            errorMsg = getString(R.string.liveness_failed_reason_muchaction);
                            break;
                    }
                    LivenessResult.setErrorMsg(errorMsg);
                    setResultData();
                    break;
            }
        }
    }


}
