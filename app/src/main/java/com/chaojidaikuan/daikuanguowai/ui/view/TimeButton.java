package com.chaojidaikuan.daikuanguowai.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * * <p>
 * Created by zhanglz on 2018/10/10.
 * describe：
 * 　　 へ　　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 * <p>
 * ===========皮卡丘保佑永无BUG============
 */

public class TimeButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    private long length = 60 * 1000;// 倒计时长度,这里给了默认60秒
    private String textafter = "秒后重新获取";
    private String textbefore = "点击获取验证码";
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    private Context mContext;
    Map<String, Long> map = new HashMap<String, Long>();

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);

    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(time / 1000 + textafter);
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        }
    };

    private void initTimer() {
        time = length;
        t = new Timer();
        tt = new TimerTask() {
            @Override
            public void run() {
                Log.e("yung", time / 1000 + "");
                han.sendEmptyMessage(0x01);
            }
        };
    }

    public void clearTimer() {
//        Toast.makeText(mContext, "计时结束", Toast.LENGTH_SHORT).show();
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnclickListener != null)
            mOnclickListener.onClick(v);

    }
    public void starTime(){
        initTimer();
        this.setText(time / 1000 + textafter);
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
    }

    /**
     * 和activity的onDestroy()方法同步
     */
//    public void onDestroy() {
//        if (MainActivity.map == null)
//            MainActivity.map = new HashMap<String, Long>();
//        MainActivity.map.put(TIME, time);
//        MainActivity.map.put(CTIME, System.currentTimeMillis());
//        clearTimer();
//    }

    /**
     * 和activity的onCreate()方法同步
     */
//    public void onCreate(Bundle bundle) {
//        Log.e("yung", MainActivity.map + "");
//        if (MainActivity.map == null)
//            return;
//        if (MainActivity.map.size() <= 0)// 这里表示没有上次未完成的计时
//            return;
//        long time = System.currentTimeMillis() - MainActivity.map.get(CTIME)
//                - MainActivity.map.get(TIME);
//        MainActivity.map.clear();
//        if (time > 0)
//            return;
//        else {
//            initTimer();
//            this.time = Math.abs(time);
//            t.schedule(tt, 0, 1000);
//            this.setText(time + textafter);
//            this.setEnabled(false);
//        }
//    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.length = lenght;
        return this;
    }
}
