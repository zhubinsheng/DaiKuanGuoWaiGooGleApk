package com.chaojidaikuan.daikuanguowai.ui.utils;

import java.text.DecimalFormat;

public class FormatTosepara {
    public static String formatTosepara(float data) {
        DecimalFormat df;
        if (data < 1000) {
            df = new DecimalFormat("#,##");
        } else {
            df = new DecimalFormat("#,###");
        }
        if (data == 0) {
            return "0.000";
        }
        return df.format(data).replace(",", ".");
    }
}
