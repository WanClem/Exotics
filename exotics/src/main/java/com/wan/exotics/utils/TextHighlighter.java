package com.wan.exotics.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.indexOfIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Wan Clem
 */

public class TextHighlighter {

    public static Spannable highlightSubString(String originalString, String subString, int color) {
        if (isNotEmpty(subString)) {
            if (containsIgnoreCase(originalString, subString.trim())) {
                int startPost = indexOfIgnoreCase(originalString, subString.trim());
                int endPost = startPost + subString.length();
                Spannable spanText = Spannable.Factory.getInstance().newSpannable(originalString);
                if (startPost != -1) {
                    spanText.setSpan(new ForegroundColorSpan(color), startPost, endPost, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    return spanText;
                } else {
                    return new SpannableString(originalString);
                }
            } else {
                return new SpannableString(originalString);
            }

        } else {
            return new SpannableString(originalString);
        }
    }

}
