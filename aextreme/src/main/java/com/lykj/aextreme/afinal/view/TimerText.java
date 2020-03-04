package com.lykj.aextreme.afinal.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.lykj.aextreme.R;


/**
 * 倒计时按钮
 */
@SuppressLint("AppCompatCustomView")
public class TimerText extends TextView implements Runnable {

    private final static int DEFAULT_INTERVAL = 120;// 间隔时间60秒
    private final static int UPDATE_END = 0;
    private final static int UPDATE_DOING = 2;
    private int timer = 0;
    private int status = 0;

    public TimerText(Context context) {
        super(context);
    }

    public TimerText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        this.status = UPDATE_END;
        this.setText(R.string.timer_send_re);
        this.setEnabled(true);
    }

    private void update(int time) {
//        this.setText(String.format(getHint().toString(), time));
         this.setText(time + "秒后重新获取");
//         this.setTextColor(getResources().getColor(R.color.login_send_hid_color));
    }

    /**
     * 准备倒计时
     */
    public void prepare() {
        this.setText(R.string.timer_sending);
        this.setEnabled(false);
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
            end();
            return;
        }
        update(timer);
        postDelayed(this, 1000);
    }
}
