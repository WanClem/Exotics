package com.wan.exotics.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import com.wan.exotics.R;

/**
 * @author Wan clem
 */

public class MetricsUtils {

    public static float density = 1;

    private static Boolean isTablet = null;

    private static int toPx(Context context, int dp) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static boolean isTablet(Context context) {
        if (isTablet == null) {
            isTablet = context.getResources().getBoolean(R.bool.isTablet);
        }
        return isTablet;
    }

}
