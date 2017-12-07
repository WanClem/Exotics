package com.wan.exotics.utils;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

/**
 * @author Wan Clem
 */

public class HtmlUtils {

    /***
     * Parses and display html content
     *
     * @param html The Html String
     *
     **/
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

}
