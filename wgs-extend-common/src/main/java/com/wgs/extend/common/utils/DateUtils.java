<<<<<<< HEAD
<<<<<<< HEAD
package com.wgs.extend.common.utils;

=======
package com.eaju.common.utils;
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
package com.eaju.common.utils;
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
<<<<<<< HEAD
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
<<<<<<< HEAD
<<<<<<< HEAD


	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

=======
	
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
	
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	/**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	/**
	 * 当前时间后移   n天 n小时  n分   n秒
	 * @方法名: timeDate  
	 * @功能描述: (这里用一句话描述这个方法的作用)
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 * @作者  GYF
	 * @日期 2018年9月27日
	 */
	public static Date timeDate(Integer hour, Integer minute, Integer second,Calendar nowTime){
		
		//Calendar nowTime = Calendar.getInstance();
		
		nowTime.add(Calendar.HOUR, hour);
		nowTime.add(Calendar.MINUTE, minute);
		nowTime.add(Calendar.SECOND, second);
		
		Date time = nowTime.getTime();
		
		return time;
	}

	public static String timeStamp(String seconds){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String substring = seconds.substring(0, 10)+"000";
		return sdf.format(new Date(Long.valueOf(substring)));
	}

	public static Date timeStampDate(String seconds){
		String substring = seconds.substring(0, 10)+"000";
		return new Date(Long.valueOf(substring));
	}

	/**
	 * 时间戳转换为日期
	 * @方法名: timeStampDate  
	 * @功能描述: (这里用一句话描述这个方法的作用)
	 * @param seconds
	 * @param format
	 * @return
	 * @作者  GYF
	 * @日期 2018年9月13日
	 */
	public static String timeStampDate(String seconds,String format) {
		 
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
             return "";  
         }  
         if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
         }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
       return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }
	/**返回日期 2018年10月12日  10:50
	 * 
	 * @方法名: timeString  
	 * @功能描述: (这里用一句话描述这个方法的作用)
	 * @return
	 * @作者  GYF
	 * @日期 2018年10月12日
	 */
	public static String timeString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String seconds = System.currentTimeMillis()+"";
		String substring = seconds.substring(0, 10)+"000";
		return sdf.format(new Date(Long.valueOf(substring)));
	}




	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	private static String formatDate(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}

	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}


	public static long period(Date date){
		return new Date().getTime() - date.getTime();
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @return
	 */
	public static String pastTime(Date date){
		long timeMillis = period(date);
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		StringBuffer buffer = new StringBuffer();
		if (day > 0) {
			buffer.append(day).append("天");
		}
		if (hour > 0) {
			buffer.append(hour).append("小时");
		}
		if (min > 0) {
			buffer.append(min).append("分钟");
		}
		if (s > 0) {
			buffer.append(s).append("秒");
		}
		if (sss >= 0) {
			buffer.append(sss).append("毫秒");
		}
		return buffer.toString();
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取两个日期之间的分钟
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static long getMinuteOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60);
	}
	/**
	 * 几分钟前 几小时前说法
	 * @param before
	 * @param after
	 * @return
	 */
	public static String getChineseForInterval(Date before, Date after) {
		long interval =getMinuteOfTwoDate(before, after);
		if(0 == interval) {
			return "刚刚";
		}
		else if(interval < 60) {
			return interval+"分钟前";
		}
		else if(interval >=60 && interval <=24*60) {
			return interval/60+"小时前";
		}
		else if(interval >24*60 && interval <=24*60*2) {
			return "昨天";
		}
		else if(interval >24*60*2 && interval <=24*60*3){
			return "前天";
		}
		else {
			return formatDate(before, "yyyy-MM-dd HH:mm");
		}
	}
	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
