package com.chaojidaikuan.daikuanguowai.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import cc.ibooker.ztextviewlib.AutoVerticalScrollTextView;


public class ScrollTextViewView extends AutoVerticalScrollTextView {
    public ScrollTextViewView(Context context) {
        super(context);
    }

    public ScrollTextViewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public View makeView() {
        TextView textView = new TextView(getContext());
        textView.setGravity(8388611);
        textView.setTextSize(14.0F);
        textView.setSingleLine(true);
        textView.setGravity(14);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.parseColor("#F9FBFC"));
        return textView;
    }
}
