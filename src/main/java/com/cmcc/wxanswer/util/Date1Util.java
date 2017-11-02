package com.cmcc.wxanswer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date1Util {
	public static String getDate1(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int year = c.get(java.util.Calendar.YEAR);
		int month = c.get(java.util.Calendar.MONTH) + 1;
		int day = c.get(java.util.Calendar.DAY_OF_MONTH);
		String m = "";
		String d = "";
		if (month < 10) {
			m = "0" + month;
		}else {
			m =""+ month;
		}
		if (day < 10) {
			d = "0" + day;
		}else {
			d =""+day;
		}
		String date = year + "年" + m + "月" + d + "日";
		return date;
	}

	public static String getDate2(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int month = c.get(java.util.Calendar.MONTH) + 1;
		int day = c.get(java.util.Calendar.DAY_OF_MONTH);
		String date = month + "月" + day + "日";
		return date;
	}

	public static Date getBeforeOrAfterDay(Date date, long day) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTimeInMillis(DateUtil.getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		Date beforeDay = startCal.getTime();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		beforeDay = DateUtil.parseDate(dateFormater.format(beforeDay), "yyyy/MM/dd");
		return beforeDay;

	}

	public static String getDate3(String date) {
		return date.substring(0, 4) + "-" + date.substring(5, 7) + "-" + date.substring(8, 10);
	}
}
