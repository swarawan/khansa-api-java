package com.swarawan.khansapos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDefaultDate(Date now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(now);
    }
}
