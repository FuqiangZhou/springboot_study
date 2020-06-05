package com.zhou.springbootredis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-07 11:36
 */
public class DateUtils {



    public static Date stringToDate(String date, String formatStr) {
        if (formatStr == null) {
            formatStr = "yyyy-MM-dd";
        }
        try {
            return new SimpleDateFormat(formatStr).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static boolean isCross(DateInterval dateInterval, DateInterval comparisonInterval) {
        return (dateInterval.getStartTime().getTime() < comparisonInterval.getStartTime().getTime()
                && dateInterval.getEndTime().getTime() > comparisonInterval.getStartTime().getTime())

                || (dateInterval.getStartTime().getTime() < comparisonInterval.getEndTime().getTime()
                && dateInterval.getEndTime().getTime() >= comparisonInterval.getEndTime().getTime())

                || (dateInterval.getStartTime().getTime() > comparisonInterval.getStartTime().getTime()
                && dateInterval.getEndTime().getTime() < comparisonInterval.getEndTime().getTime())

                || (dateInterval.getStartTime().getTime() == comparisonInterval.getStartTime().getTime()
                && dateInterval.getEndTime().getTime() >= comparisonInterval.getEndTime().getTime());
    }

    public static class DateInterval extends HashMap<String, Date> {

        public void setInterval(Date startTime, Date endTime){
            this.put("startTime", startTime);
            this.put("endTime", endTime);
        }

        Date getStartTime(){
            return this.get("startTime");
        }

        Date getEndTime(){
            return this.get("endTime");
        }
    }
}
