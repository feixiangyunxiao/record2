package com.game.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
    // 将date类型的数据转换成字符串
    public static String datachange(Date olddate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(olddate);
        return format;
    }

    // 将字符串转化为date类型的变量
    public static Date stringToDate(String stringTime) {
        Date resultDate = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            resultDate = simpleDateFormat.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }
}
