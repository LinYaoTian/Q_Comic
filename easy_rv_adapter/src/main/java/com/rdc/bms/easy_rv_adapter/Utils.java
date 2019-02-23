package com.rdc.bms.easy_rv_adapter;

import android.content.Context;

public class Utils {
    /**
     *  dp 转换 px
     * @param context
     * @param dip
     * @return
     */
    public static int dpToPx(Context context, float dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);
        return valuePixels;
    }


}
