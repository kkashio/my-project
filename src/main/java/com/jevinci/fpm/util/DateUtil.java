package com.jevinci.fpm.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
public class DateUtil {
    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        LocalDateTime ldt = localDateTime;
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
