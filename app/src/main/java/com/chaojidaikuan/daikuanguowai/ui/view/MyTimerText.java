package com.chaojidaikuan.daikuanguowai.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.chaojidaikuan.daikuanguowai.R;


@SuppressLint("AppCompatCustomView")
public class MyTimerText extends TextView implements Runnable {

    private final static int DEFAULT_INTERVAL = 120;// 间隔时间60秒
    private final static int UPDATE_END = 0;
    private final static int UPDATE_DOING = 2;
    private int timer = 0;
    private int status = 0;

    public MyTimerText(Context context) {
        super(context);
    }

    public MyTimerText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTimerText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private BackEndTime backEndTime;

    public void setBackEndTime(BackEndTime backEndTime) {
        this.backEndTime = backEndTime;
    }

    /**
     * 开启倒计时
     */
    public void start() {
        start(DEFAULT_INTERVAL);
    }

    /**
     * 结束倒计时
     */
    public void end() {
        this.timer=UPDATE_END;
        this.status = UPDATE_END;
        this.setTextColor(getResources().getColor(R.color.login_send_color));
        this.setText(R.string.lg_tv_yz);
        this.setEnabled(true);
    }

    private void update(int time) {
        this.setText( time +"s");
        this.setTextColor(getResources().getColor(R.color.login_send_hid_color));
    }

    /**
     * 开启倒计时
     *
     * @param interval 倒计时时间(秒)
     */
    public void start(int interval) {
        if (status == UPDATE_DOING)
            return;
        this.status = UPDATE_DOING;
        this.setEnabled(false);
        this.timer = interval;
        run();
    }

    @Override
    public void run() {
        timer--;
        if (timer < 1) {
            backEndTime.backEndTimer();
            end();
            return;
        }
        update(timer);
        postDelayed(this, 1000);
    }
  public   interface BackEndTime{
        void  backEndTimer();
    }
}
