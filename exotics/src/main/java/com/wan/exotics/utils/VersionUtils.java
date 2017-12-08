package com.wan.exotics.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.wan.exotics.loggers.ExoticsLogger;

/**
 * @author Wan Clem
 */

public class VersionUtils {

    private static String TAG = VersionUtils.class.getSimpleName();

    public static String getAppVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ExoticsLogger.e(TAG, "Could not get package name: " + e);
        }
        return null;
    }

}
