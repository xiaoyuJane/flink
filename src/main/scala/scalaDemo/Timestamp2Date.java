package scalaDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Timestamp2Date {

    public static void main(String[] args) throws ParseException {
        //==========test data start
        long fault_time = System.currentTimeMillis(); //1649749674766
        final Map<String,String> filter_time = new HashMap<String,String>(){{
            put("Monday","10:00:00-12:00:00");
            put("Tuesday","08:00:11-24:00:00");
        }};
        //==========test data end

        Solution solution = new Solution();
        System.out.println(solution.weekAndTimeFilter(filter_time,fault_time));
    }


    private static class Solution {
        private  boolean weekAndTimeFilter(Map<String,String> filter_time, long time) throws ParseException {
            // timestamp
            System.out.println(time); //1649749674766
/*
        //date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(time);
        System.out.println(date); //2022-04-12 15:47:54

        //weekday and time
        SimpleDateFormat formatWeekTime = new SimpleDateFormat("EEEE HH:mm:ss");
        System.out.println(formatWeekTime.format(time));
*/

            //step1 ---通过时间戳获取星期和时间
            //week
            SimpleDateFormat formatWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            String week = formatWeek.format(time);
            System.out.println(week); //Tuesday

            //time
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
            String weekTime = formatTime.format(time);
            System.out.println(weekTime); //16:10:52

            //step2 ---判断时间是否落在时间区间内
            if (!filter_time.containsKey(week)) return false;

            String filter_Hms = filter_time.get(week);
            System.out.println(filter_Hms);
            String Time_down = filter_Hms.split("-")[0];
            String Time_up = filter_Hms.split("-")[1];

            Date Time1 = formatTime.parse(Time_down);
            Date Time2 = formatTime.parse(Time_up);
            Date TimeToCompare =  formatTime.parse(weekTime);

            return TimeToCompare.compareTo(Time1) >= 0 && TimeToCompare.compareTo(Time2) <= 0;

        }

    }
}
