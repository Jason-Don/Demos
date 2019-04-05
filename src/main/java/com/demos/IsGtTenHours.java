package com.demos;

import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

/**
 * 当前时间是否大于等于10点
 * @author
 *
 */
public class IsGtTenHours {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date ten = null;
        Date now = null;
        try {
            ten = format.parse("08:30");
            now = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(now.after(ten));
    }
}
