package com.example.pdd.mymvcapplication;

import android.provider.ContactsContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java 时间戳 各个格式的日期转换
 */
public class Testtime {

    public static void main(String[] args) {

        DateUtil dateUtil = new DateUtil();

        System.out.println(dateUtil.gettimestamp());

        String  gettimestamp =dateUtil.gettimestamptoString(dateUtil.gettimestamp(),"yyyy-MM-dd HH:mm:ss");
        System.out.println(gettimestamp);

        System.out.println(dateUtil.getstringtotimestamp(gettimestamp));

    }
}
