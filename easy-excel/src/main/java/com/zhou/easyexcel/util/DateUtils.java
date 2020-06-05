package com.zhou.easyexcel.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-04-16 18:21
 */
public class DateUtils {

    public static final String DEFAULT_DATE = "yyyy-MM-dd";

    public static final String DEFAULT_DATE_MONTH = "yyyy-MM";

    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_TIME = "HH:mm:ss";

    public static final String DEFAULT_REGX_DATE = "\\d{4}-\\d{2}-\\d{2}";

    private static final String REGX_DATE_TIME = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

    /**
     * 默认格式化
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 字符串转时间
     *
     * @param date      时间字符串
     * @param formatStr 格式化
     * @return
     */
    public static Date stringToDate(String date, String formatStr) {
        try {
            if (StringUtils.isBlank(date)){
                return new SimpleDateFormat(DEFAULT_FORMAT).parse("1900-01-01");
            }
            if (formatStr == null) {
                formatStr = DEFAULT_FORMAT;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 时间字符串格式化
     *
     * @param date      时间字符串
     * @param formatStr 格式化
     * @return
     */
    public static String dateStringFormat(String date, String formatStr) {
        try {
            if (formatStr == null) {
                formatStr = DEFAULT_FORMAT;
            }
            Date d;
            if (Pattern.matches(REGX_DATE_TIME, date)){
                d = new SimpleDateFormat(DEFAULT_DATE_TIME).parse(date);
            }else if (Pattern.matches(DEFAULT_REGX_DATE, date)){
                d = new SimpleDateFormat(DEFAULT_DATE).parse(date);
            }else {
                d = new SimpleDateFormat(DEFAULT_DATE).parse(date);
            }
            return new SimpleDateFormat(formatStr).format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * 时间转字符串
     *
     * @param date      时间
     * @param formatStr 格式化
     * @return
     */
    public static String dateToString(Date date, String formatStr) {
        if (formatStr == null) {
            formatStr = DEFAULT_FORMAT;
        }
        return new SimpleDateFormat(formatStr).format(date);
    }

    /**
     * 获取n天后
     *
     * @param date 指定日期
     * @param next n天
     * @return
     */
    public static Date afterDay(Date date, int next) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, next);
        return calendar.getTime();
    }

    public static Date afterHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    public static Date afterMin(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    /**
     * 获取未来几天的日期字符串集合
     *
     * @param date        指定日期
     * @param future      未来几天
     * @param containDate 是否包含指定日期
     * @param formatStr   格式化
     * @return
     */
    public static List<String> futureDays(Date date, int future, boolean containDate, String formatStr) {
        List<String> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (containDate) {
            future = future - 1;
            days.add(dateToString(calendar.getTime(), formatStr));
        }
        for (int i = 0; i < future; i++) {
            calendar.add(Calendar.DATE, 1);
            days.add(dateToString(calendar.getTime(), formatStr));
        }
        return days;
    }

    /**
     * 获取未来几天的日期集合
     *
     * @param date        指定日期
     * @param future      未来几天
     * @param containDate 是否包含指定日期
     * @return
     */
    public static List<Date> futureDayDates(Date date, int future, boolean containDate) {
        List<Date> dayDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (containDate) {
            future = future - 1;
            dayDates.add(date);
        }
        for (int i = 0; i < future; i++) {
            calendar.add(Calendar.DATE, 1);
            dayDates.add(calendar.getTime());
        }
        return dayDates;
    }

    /**
     * 获取未来几天指定的星期几的日期集合
     *
     * @param date    指定日期
     * @param future  未来几天
     * @param weekDay 指定星期几
     * @return
     */
    public static List<Date> futureDatesOfWeek(Date date, int future, int weekDay) {
        List<Date> dates = new ArrayList<>();
        List<Date> list = futureDayDates(date, future, true);
        for (Date d : list) {
            if (weekDay == getWeek(d)) {
                dates.add(stringToDate(dateToString(d, DEFAULT_FORMAT), DEFAULT_FORMAT));
            }
        }
        return dates;
    }

    /**
     * 获取未来几天的日期字符串集合并忽略指定星期
     *
     * @param date        指定日期
     * @param future      未来几天
     * @param ignoreWeeks 忽略星期
     * @param formatStr   格式化
     * @return
     */
    public static List<String> futureDaysWithIgnoreWeeks(Date date, int future, Set<Integer> ignoreWeeks, String formatStr) {
        List<String> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = 0;
        while (i < future) {
            if (ignoreWeeks.contains(getWeek(calendar.getTime()))) {
                calendar.add(Calendar.DATE, 1);
                continue;
            }
            days.add(dateToString(calendar.getTime(), formatStr));
            calendar.add(Calendar.DATE, 1);
            i++;
        }

        return days;
    }

    /**
     * 获取未来几天的日期字符串集合并忽略指定日期
     *
     * @param date        指定日期
     * @param future      未来几天
     * @param ignoreDates 忽略日期
     * @param formatStr   格式化
     * @return
     */
    public static List<String> futureDaysWithIgnoreDates(Date date, int future, Set<Date> ignoreDates, String formatStr) {
        List<String> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = 0;
        while (i < future) {
            if (ignoreDates.contains(stringToDate(dateToString(calendar.getTime(), formatStr), formatStr))) {
                calendar.add(Calendar.DATE, 1);
                continue;
            }
            days.add(dateToString(calendar.getTime(), formatStr));
            calendar.add(Calendar.DATE, 1);
            i++;
        }
        return days;
    }

    /**
     * 获取星期
     *
     * @param date 指定日期
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取两个时间之间的日期集合(闭合区间)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static Set<Date> getDates(Date startDate, Date endDate) {
        Set<Date> dates = new HashSet<>();
        try {
            startDate = new SimpleDateFormat(DEFAULT_FORMAT).parse(new SimpleDateFormat(DEFAULT_FORMAT).format(startDate));
            endDate = new SimpleDateFormat(DEFAULT_FORMAT).parse(new SimpleDateFormat(DEFAULT_FORMAT).format(endDate));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            boolean flag = true;
            dates.add(startDate);
            while (flag) {
                calendar.add(Calendar.DATE, 1);
                dates.add(calendar.getTime());
                if (endDate.getTime() == calendar.getTimeInMillis()) {
                    flag = false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    /**
     * 功能描述: 计算时间差值
     * @author 周富强
     * @date 2019-07-29 16:40
     * @param subtractionDate 减日期
     * @param subtractedDate 被减日期
     * @param timeEnum 时间类型枚举
     * @return
     */
    public static long subDate(Date subtractionDate, Date subtractedDate, TimeEnum timeEnum){
        Calendar subtractionCalendar = Calendar.getInstance();
        Calendar subtractedCalendar = Calendar.getInstance();
        subtractionCalendar.setTime(subtractionDate);
        subtractedCalendar.setTime(subtractedDate);
        long sub = subtractionCalendar.getTimeInMillis() - subtractedCalendar.getTimeInMillis();
        if (timeEnum == TimeEnum.YEAR){
            return subtractionCalendar.get(Calendar.YEAR) - subtractedCalendar.get(Calendar.YEAR);
        }
        if (timeEnum == TimeEnum.DAY){
            return sub / (3600 * 24 * 1000);
        }
        if (timeEnum == TimeEnum.HOUR){
            return sub / (3600 * 1000);
        }
        if (timeEnum == TimeEnum.MINUTE){
            return sub / (60 * 1000);
        }
        if (timeEnum == TimeEnum.SECOND){
            return sub / 1000;
        }
        return sub;
    }


    public enum TimeEnum{
        /**
         * 年
         */
        YEAR(0),
        /**
         * 天
         */
        DAY(1),
        /**
         * 小时
         */
        HOUR(2),
        /**
         * 分钟
         */
        MINUTE(3),
        /**
         * 秒
         */
        SECOND(4);
        TimeEnum(int value) {
        }
    }
}
