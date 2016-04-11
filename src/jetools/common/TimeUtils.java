package jetools.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
	
	/**
	 * 获得当前世界协调时UTC
	 * */
	public static String getUTC() {
		Calendar cal = GregorianCalendar.getInstance();
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		Date date = new Date(cal.getTimeInMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 将时间戳转换为世界协调时UTC
	 * */
	public static String timestamp2UTC(long timestamp) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(timestamp);
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		Date date = new Date(cal.getTimeInMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 获得当前时间戳，毫秒级
	 * */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 获得当前时间戳，秒级
	 * */
	public static long currentTimeSecond() {
		return timeSecond(currentTimeMillis());
	}
	
	/**
	 * 获得当前时间
	 * */
	public static String currentDate() {
		return timestamp2Time(currentTimeMillis());
	}
	
	/**
	 * 将毫秒级时间戳转为秒级
	 * */
	public static long timeSecond(long timeMillis) {
		return timeMillis / 1000;
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的时间转换为时间戳
	 * 返回值为毫秒级
	 * */
	public static long time2Timestamp(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timestamp = -1;
		try {
			timestamp = format.parse(time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	
	/**
	 * 将yyyy-MM-dd格式的时间转换为时间戳
	 * 返回值为毫秒级
	 * */
	public static long daytime2Timestamp(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long timestamp = -1;
		try {
			timestamp = format.parse(time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	
	/**
	 * 将int型yyyyMMDD格式的时间转换为时间戳
	 * 返回值为毫秒级
	 * */
	public static long daytime2Timestamp(int time) {
		//将time变为yyyy-MM-dd格式字符串
		String timeStr = time + "";
		if(timeStr.length() != 8) {
			return -1;
		}
		String y = timeStr.substring(0, 4);
		String m = timeStr.substring(4, 6);
		String d = timeStr.substring(6, 8);
		return daytime2Timestamp(y + "-" + m + "-" + d);
	}
	
	/**
	 * 将时间戳转换为yyyy-MM-dd HH:mm:ss格式的时间
	 * */
	public static String timestamp2Time(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(timestamp));
	}
	
	public static String timestamp2Time(long timestamp, int timezone) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(timestamp);
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		int dstZoneOffset = (int)TimeUnit.HOURS.toMillis(timezone);
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		cal.add(Calendar.MILLISECOND, -(zoneOffset - dstZoneOffset + dstOffset));
		Date date = new Date(cal.getTimeInMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 将时间戳转换为yyyy-MM-dd格式的时间
	 * */
	public static String timestamp2DayTime(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date(timestamp));
	}
	
	/**
	 * 将时间戳转换为yyyyMMdd格式int型的时间
	 * */
	public static int timestamp2DayTimeInt(long timestamp) {
		String dayTime = timestamp2DayTime(timestamp);
		String y = dayTime.substring(0, 4);
		String m = dayTime.substring(5, 7);
		String d = dayTime.substring(8, 10);
		String time = y + m + d;
		return Integer.parseInt(time);
	}
	
	public static int getMonthStartDayTimeInt(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String monthTime = format.format(new Date(timestamp));
		String y = monthTime.substring(0, 4);
		String m = monthTime.substring(5, 7);
		String time = y + m + "01";
		return Integer.parseInt(time);
	}
	
	/**
	 * 将时间戳转换为yyyyMMddHHmmssSSS格式
	 * */
	public static String timestamp2TimeString(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date(timestamp));
	}
	
	/**
	 * 获得某一天开始时间戳
	 * 传入日期格式为yyyy-MM-dd
	 * */
	public static long getDayStartTimestamp(String dayTime) {
		String time = dayTime + " 00:00:00";
		return time2Timestamp(time);
	}
	
	/**
	 * 获得某一天开始时间戳
	 * 参数为改天某一刻的时间戳
	 * */
	public static long getDayStartTimestamp(long dayTimestamp) {
		return getDayStartTimestamp(timestamp2DayTime(dayTimestamp));
	}
	
	/**
	 * 获得某一天结束时间戳
	 * 传入日期格式为yyyy-MM-dd
	 * */
	public static long getDayEndTimestamp(String dayTime) {
		long dayTimestamp = daytime2Timestamp(dayTime);
		return getDayEndTimestamp(dayTimestamp);
	}
	
	/**
	 * 获得某一天结束时间戳
	 * 参数为改天某一刻的时间戳
	 * */
	public static long getDayEndTimestamp(long dayTimestamp) {
		dayTimestamp += (24 * 60 * 60 * 1000);
		return getDayStartTimestamp(dayTimestamp) - 1;
	}
	
	/**
	 * 获得当前开始时间
	 * */
	public static String getTodayStartTime() {
		return timestamp2Time(getTodayStartTimestamp());
	}
	
	/**
	 * 获得当天开始时间戳
	 * */
	public static long getTodayStartTimestamp() {
		return getDayStartTimestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获得当天结束时间戳
	 * */
	public static long getTodayEndTimestamp() {
		long timeInterval = 24*60*60*1000;
		return getTodayStartTimestamp() + timeInterval;
	}
	
	/**
	 * 根据传入秒数得出毫秒数
	 * */
	public static long getMillisFromSeconds(int seconds) {
		return seconds * 1000;
	}
	
	/**
	 * 根据传入分钟数得出毫秒数
	 * */
	public static long getMillisFromMinutes(int minutes) {
		return getMillisFromSeconds(minutes * 60);
	}
	
	/**
	 * 根据传入小时数得出毫秒数
	 * */
	public static long getMillisFromHours(int hours) {
		return getMillisFromMinutes(hours * 60);
	}
	
	/**
	 * 根据传入天数数得出毫秒数
	 * */
	public static long getMillisFromDays(int days) {
		return getMillisFromHours(days * 24);
	}
	
	/**
	 * 将毫秒转换为秒
	 * */
	public static int millis2Second(long millis) {
		return (int)(millis / 1000);
	}
	
	/**
	 * 将秒转换为毫秒
	 * */
	public static long second2Millis(long second) {
		return second * 1000;
	}
}
