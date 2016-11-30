package com.logistics.wuliuapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * @author yh 时间转换工具类
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {

    public static int getNowTime() {
        Date date = new Date();
        int currentTime = (int) ((date.getTime()) / 1000);

        return currentTime;
    }

    /**
     * 通过时间戳得到date
     * 
     * @param time
     *            时间戳
     * @return
     */
    public static Date getDateByInt(int time) {
        Date date = new Date();
        date.setTime(time * 1000L);
        return date;
    }

    /**
     * 将日期格式的字符串转为时间戳
     * 
     * @param user_time
     * @return
     */
    public static int getTime(String user_time, String format) {
        int re_time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d;
        try {
            d = sdf.parse(user_time);
            re_time = (int) (d.getTime() / 1000);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * @param time
     * @param format
     * @return
     */
    public static String getTimeStrFromLong(long time, String format) {

        Date de = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String str = simpleDateFormat.format(de);
        return str;
    }

    /**
     * 把时间戳转换成字符串
     * 
     * @param unixTimeTick
     *            : 需要转换的时间戳
     * @param format
     *            : 转换过来的时间格式
     *            <p/>
     *            格式：
     *            <p/>
     *            yyyyMMddHHmm
     *            <p/>
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String intToString(int unixTimeTick, String format) {
        Date de = new Date(unixTimeTick * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String str = simpleDateFormat.format(de);
        return str;
    }

    /**
     * 把时间戳转换成字符串
     * 
     * @param unixTimeTick
     *            : 需要转换的时间戳
     * @param format
     *            : 转换过来的时间格式
     *            <p/>
     *            格式：
     *            <p/>
     *            yyyyMMddHHmm
     *            <p/>
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String longToString(long unixTimeTick, String format) {
        Date de = new Date(unixTimeTick);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String str = simpleDateFormat.format(de);
        return str;
    }

    /**
     * 字符串转换成统时间.
     * 
     * @param str
     *            协议中的时间串.
     * @return 系统时间.
     */
    public static Date getDateFromTimeString(String str, String format) {
        Date dt = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            dt = sdf.parse(str);
        } catch (ParseException e) {

        }
        return dt;
    }

    /**
     * 日期格式化为字符串
     * 
     * @param date
     *            系统时间
     * @param formatStr
     *            格式化字符串，如"yyyy-MM-dd HH:mm:ss"
     * @return 格式化后的时间串.
     */
    public static String dateToString(Date date, String formatStr) {

        DateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return str;
    }

    public static String getStringSimplefromInt(int count) {
        if (count == 0)
            return "--";
        String strs = "";
        String min, sec/* , ssstr */;

        int n = count;
        // 总秒数
        int ss = n / 1000;

        // 总分钟数
        int mm = ss / 60;

        // 秒数
        int s = ss % 60;

        if (s < 10) {
            sec = "0" + s;
        } else {
            sec = "" + s;
        }

        if (mm < 10) {
            min = "0" + mm;
        } else {
            min = "" + mm;
        }

        strs = min + ":" + sec;

        return strs;

    }

    /**
     * 把字符串转换成数字
     * 
     * @param timeStr
     *            : 总用时
     * @return
     */
    public static int getIntFromString(String timeStr) {
        // String[] strs = timeStr.split(" ");
        // int hm = Integer.parseInt(strs[1]);
        // hm = hm * 10;

        // String[] str2 = strs[0].split(":");
        String[] str2 = timeStr.split(":");
        if (str2.length == 3) {
            int hh = Integer.parseInt(str2[0]);
            int mm = Integer.parseInt(str2[1]);
            int ss = Integer.parseInt(str2[2]);
            hh = hh * 60 * 60 * 1000;
            mm = mm * 60 * 1000;
            ss = ss * 1000;
            int count = hh + mm + ss;
            return count;
        }
        return 0;
    }

    public static String getSection(int startTime, int endTime) {
        String sectionStr = "";
        String startStr = "开始";
        if (startTime != 0) {
            startStr = DateUtil.intToString(startTime, "HH:mm:ss");
        }
        String endStr = "结束";
        if (endTime != 0) {
            endStr = DateUtil.intToString(endTime, "HH:mm:ss");
        }
        sectionStr = startStr + "~" + endStr;
        return sectionStr;
    }

    /**
     * 返回年份
     * 
     * @param date
     *            日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     * 
     * @param date
     *            日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDayCount() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;

    }

    /**
     * 返回日份
     * 
     * @param date
     *            日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     * 
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     * 
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     * 
     * @param date
     *            日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     * 
     * @param date
     *            日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 获取当前的时间段 5:30 - 7:30 早上 0 8:30 - 11:30 上午 1 13:30 - 17:30 下午 2 18:30 -
     * 21:30 晚上 3 其他时间 休息 4
     * 
     * @param date
     * @return
     */

    @SuppressWarnings("deprecation")
    public static int getCurrentTime(Date date) {

        int hour = date.getHours();
        int minute = date.getMinutes();

        if (hour >= 5 && hour <= 7) {

            if (hour == 5 || hour == 7) {
                //
                if (minute < 30) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }

        }

        if (hour >= 8 && hour <= 11) {

            if (hour == 8 || hour == 11) {
                if (minute < 30) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 1;
            }

        }
        if (hour >= 13 && hour <= 17) {

            if (hour == 13 || hour == 17) {
                if (minute < 30) {
                    return 2;
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        }
        if (hour > 18 && hour <= 21) {

            if (hour == 18 || hour == 21) {
                if (minute < 30) {
                    return 3;
                } else {
                    return 4;
                }
            } else {
                return 3;
            }
        } else {
            return 4;
        }
    }
}
