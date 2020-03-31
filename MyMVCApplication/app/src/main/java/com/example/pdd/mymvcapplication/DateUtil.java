package com.example.pdd.mymvcapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 获取时间戳
     */
    public long gettimestamp(){

        long timestamp = System.currentTimeMillis();
        return timestamp;

    }

    /**
     * 获取当前时间
     */
    public String getCurDate(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return simpleDateFormat.format(new java.util.Date());

    }

    /**
     * 将时间戳转换为固定格式的日期,常见的固定格式有 yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日"
     */

    public String gettimestamptoString(long timestamp ,String patter){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);

        Date date = new Date(timestamp);

        String  timestamptostring  = simpleDateFormat.format(date);

        return timestamptostring;
    }

    /**
     *将字符串转换为时间戳
     */

    public long  getstringtotimestamp(String patter){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//传递过来的数据要和simpleDateFormat对应的数据格式相同，否则会有异常报出
        Date date1 = new Date();
        try {
            date1 = (Date) simpleDateFormat.parse(patter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timestamp01 = date1.getTime();

        return timestamp01;
    }

}
