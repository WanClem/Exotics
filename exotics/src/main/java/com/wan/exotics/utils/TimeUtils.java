package com.wan.exotics.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author Wan Clem
 */

public class TimeUtils {

    public static final SimpleDateFormat DATE_FORMATTER_IN_GEN_FORMAT = new SimpleDateFormat("d/MM/yyyy", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMATTER_IN_12HRS = new SimpleDateFormat("h:mm a", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMATTER_IN_BIRTHDAY_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMATTER_IN_YEARS = new SimpleDateFormat("yyyy", Locale.getDefault());
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());

    public static String getTimeAgo(Date past) {
        Date now = new Date();
        String time;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
        if (minutes <= 59) {
            if (minutes < 1) {
                time = "a minute ago";
            } else if (minutes == 1) {
                time = "just now";
            } else {
                time = minutes + " mins ago";
            }
        } else {
            long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
            if (hours <= 24) {
                if (hours == 24) {
                    time = "an hour ago";
                } else {
                    time = hours + " hours ago";
                }
            } else {
                if (hours <= 48) {
                    time = "yesterday, " + DATE_FORMATTER_IN_12HRS.format(past);
                } else {
                    String currentYear = DATE_FORMATTER_IN_YEARS.format(now);
                    time = StringUtils.strip(DATE_FORMATTER_IN_BIRTHDAY_FORMAT.format(past), currentYear);
                }
            }
        }
        return time;
    }

    public static String getTimeString(long millis) {
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        return String.format(Locale.getDefault(), "%02d", minutes) +
                ":" +
                String.format(Locale.getDefault(), "%02d", seconds);
    }

}
