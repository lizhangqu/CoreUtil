package zafu.edu.cn.coreutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 14:10
 */
public class DatetimeUtil {
    private static final String DEFAULT_FORMAT1="yyyy-MM-dd HH:mm:ss";
    //默认时间格式1 年-月-日 时:分:秒
    private static final String DEFAULT_FORMAT2="yyyy-MM-dd";
    //默认时间格式2 年-月-日
    public static final long DAY_TIME_MILLIS = 24 * 60 * 60 * 1000;
    //一天的毫秒数
    /**
     * 获得当前时间的字符串
     * @param format 格式化字符串，如"yyyy-MM-dd HH:mm:ss"
     * @return String类型的当前日期时间
     */
    public static String getCurrentDatetime(String format){
        String currentDateTime=null;
        if (null==format||"".equals(format)){
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Calendar calendar = new GregorianCalendar();
            currentDateTime = simpleDateFormat.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return currentDateTime;
    }

    /**
     * 得到当前时间的格式化时间
     * @return 年-月-日 时:分:秒 格式的当前时间
     */
    public static String getCurrentDatetime(){
        return getCurrentDatetime(DEFAULT_FORMAT1);
    }

    /**
     * 获得指定时间的date格式
     * @param date 指定时间Date类型
     * @param format 格式化字符串
     * @return 格式化完后的时间
     */
    public static String getCurrentDate(Date date,String format){
        String currentDate=null;
        try {
            SimpleDateFormat formatter=new SimpleDateFormat(format);
            currentDate=formatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return currentDate;

    }

    /**
     * 获得指定毫秒数，指定格式的格式化时间
     * @param time 毫秒数
     * @param format 格式化字符串
     * @return 格式化后的时间
     */
    public static String getCurrentDate(long time,String format){
        Date date=new Date(time);
        return getCurrentDate(date, format);
    }

    /**
     * 获得指定Date类型的格式化时间
     * @param date 时间Date
     * @return 格式化后的时间
     */
    public static String getCurrentDate(Date date){
        return getCurrentDate(date,DEFAULT_FORMAT2);
    }

    /**
     * 获得指定毫秒数的格式化时间
     * @param time 毫秒数
     * @return 格式化后的时间
     */
    public static String getCurrentDate(long time){
        return getCurrentDate(time,DEFAULT_FORMAT2);
    }

    /**
     * 获得当前时间指定格式化字符串的格式化时间
     * @param format 格式化字符串
     * @return 格式化后的时间
     */
    public static String getCurrentDate(String format){
        return getCurrentDate(System.currentTimeMillis(),format);
    }
    /**
     * 获得当前时间的格式化时间
     * @return 格式化后的时间
     */
    public static String getCurrentDate(){
        return getCurrentDate(System.currentTimeMillis(), DEFAULT_FORMAT2);
    }

    /**
     * 获得指定Date类型的毫秒数
     * @param date 指定的Date
     * @return 指定Date类型的毫秒数
     */
    public static long getTimeMillis(Date date){
        return date.getTime();
    }

    /**
     * 获得当前时间的毫秒数
     * @return 当前时间的毫秒数
     */
    public static long getCurrentDayTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 得到当前时间后一天的TimeMillis
     * @return 当前时间后一天的TimeMillis
     */
    public static long getNextDayTimeMillis(){
        return getCurrentDayTimeMillis()+DAY_TIME_MILLIS;
    }

    /**
     * 得到当前时间前一天的TimeMillis
     * @return 当前时间前一天的TimeMillis
     */
    public static long getPreDayTimeMillis(){
        return getCurrentDayTimeMillis()-DAY_TIME_MILLIS;
    }
    /**
     * 根据输入的i,i 为 calendar的星期索引,返回星期几
     * @param i calendar的星期索引
     * @return 星期几
     */
    public static String getDateOfWeek(int i){
        String date=null;
        switch (i){
            case Calendar.SUNDAY:
                date = "星期日";
                 break;
            case Calendar.MONDAY:
                date = "星期一";
                break;
            case Calendar.TUESDAY:
                date = "星期二";
                break;
            case Calendar.WEDNESDAY:
                date = "星期三";
                break;
            case Calendar.THURSDAY:
                date = "星期四";
                break;
            case Calendar.FRIDAY:
                date = "星期五";
                break;
            case Calendar.SATURDAY:
                date = "星期六";
                break;
            default:
                break;
        }
        return  date;
    }

    /**
     * 根据输入的i,i 为 calendar的星期索引,返回星期几
     * @param i calendar的星期索引
     * @return 周几
     */
    public static String getSimpleDateOfWeek(int i){
        String date=null;
        switch (i){
            case Calendar.SUNDAY:
                date = "周日";
                break;
            case Calendar.MONDAY:
                date = "周一";
                break;
            case Calendar.TUESDAY:
                date = "周二";
                break;
            case Calendar.WEDNESDAY:
                date = "周三";
                break;
            case Calendar.THURSDAY:
                date = "周四";
                break;
            case Calendar.FRIDAY:
                date = "周五";
                break;
            case Calendar.SATURDAY:
                date = "周六";
                break;
            default:
                break;
        }
        return  date;
    }
}
