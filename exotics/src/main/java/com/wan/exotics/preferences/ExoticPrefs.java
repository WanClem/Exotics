package com.wan.exotics.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Wan Clem
 */

public class ExoticPrefs {

    public static SharedPreferences getInstance(Context context, String prefName) {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

}
