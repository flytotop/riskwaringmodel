package com.emsoft.riskwaring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * GetTimeUtils
 *
 * @author {yuanwei}
 * @date 2019/4/12 13:54
 */
public class GetTimeUtils {
//    public static void main(String[] args) throws Exception{
//        System.out.println(getPeriodOneUtils("201804","201908"));
//    }


    /**
     * 获得本年年初
     */
    public static String getBeginMouth(String year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        String beginMonth = sdf.format(calendar.getTime());
        return beginMonth;
    }

    /**
     * 获得上个月月份
     */
    public static String getLastMouth(String period) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        //calendar.set(2010,5,0);
        Date date = null;
        try {
            date = sdf.parse(period);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        //calendar.add(Calendar.MONTH, -1);
        //取得上一个月时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        String lastMonth = sdf.format(calendar.getTime());
        return lastMonth;
    }

    /**
     * 获得前一天
     */
    public static String getLastDay(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        //calendar.add(Calendar.MONTH, -1);
        //取得上一个月时间
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }

    public static String getAddDay(String day, int dayNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        //calendar.add(Calendar.MONTH, -1);
        //取得上一个月时间
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + dayNum);
        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }

    public static List<String> getPeriodOneUtils(String begin, String ending) {
        List<String> list = new ArrayList<>();
        Date d1 = null;
        try {
            d1 = new SimpleDateFormat("yyyyMM").parse(begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date d2 = null;
        try {
            d2 = new SimpleDateFormat("yyyyMM").parse(ending);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar dd = Calendar.getInstance();

        dd.setTime(d1);

        while (dd.getTime().before(d2)) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

            String str = sdf.format(dd.getTime());
            list.add(str);
            dd.add(Calendar.MONTH, 1);
        }
        list.add(ending);
        return list;
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    private static Calendar calendar = Calendar.getInstance();

    public static Date getMAXDateMonth(String month) {
        try {
            Date nowDate = sdf.parse(month);
            calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getStringDayToDate(String day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date nowDate = sdf.parse(day);
            calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getStringDayToDate(String day, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date nowDate = sdf.parse(day);
            calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Integer formatDateToInteger(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String dateString = df.format(date);
        String s = dateString.replaceAll("-", "");
        String s1 = s.replaceAll(" ", "");
        String s2 = s1.replaceAll(":", "");
        return Integer.valueOf(s2);
    }

    public static Date initMonthBeforeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        cal.add(Calendar.MONTH, 0);//测试使用当月
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time = cal.getTime();
        return time;

    }

    public static boolean isValidDate(String str, String formatStr) {
        boolean result = true;
        //判断字符串长度是否为8位
        if (str.length() >= 4) {
            // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
            //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            try {
                // 设置lenient为false.
                // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
                format.setLenient(false);
                format.parse(str);
            } catch (ParseException e) {
                // e.printStackTrace();
                // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }
}
