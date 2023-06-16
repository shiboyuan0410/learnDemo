package com.example.learndemo.dateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 多线程的处理
     * 创建副本进行操作
     */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };

    /**
     * string日期格式数据 转换为 时间戳毫秒值
     * @param dateTime
     * @return
     * @throws ParseException
     */
    public static Long str2long(String dateTime) throws ParseException {
        Date start_date = threadLocal.get().parse(dateTime);
        return start_date.getTime();
    }

    /**
     * 时间戳毫秒值 转换为 string日期格式数据
     * @param dateTime
     * @return
     */
    public static String long2str(Long dateTime) {
        Date date = new Date(dateTime);
        return threadLocal.get().format(date);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurDate() {
        Date date = new Date();
        return threadLocal.get().format(date);
    }

}
