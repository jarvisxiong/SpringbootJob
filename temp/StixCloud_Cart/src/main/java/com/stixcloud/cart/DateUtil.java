/**
 * This is a utility class for formating date objects
 */
package com.stixcloud.cart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {

  public static final String DATE_FORMAT_DEFAULT_FOR_DATE_AND_TIME = "dd-MM-yyyy HH:mm";

  public static final String DATE_FORMAT_DEFAULT_FOR_DATE_AND_TIME_12HOUR = "dd-MM-yyyy h:mm a";

  public static final String
      DATE_FORMAT_DEFAULT_FOR_DATE_AND_TIME_AND_SECONDS =
      "dd-MM-yyyy HH:mm:ss";

  public static final String DATE_FORMAT_DEFAULT_FOR_DATE = "dd-MM-yyyy";

  public static final String DATE_FORMAT_DEFAULT_FOR_HOUR_MINUTE = "HH:mm";

  public static final String DATE_FORMAT_DEFAULT_FOR_HOUR_MINUTE_SECONDS = "HH:mm:ss";

  public static final String DATE_FORMAT_DEFAULT_FOR_DAY_DATE_AND_TIME = "EEE, dd-MM-yyyy HH:mm";

  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DATE = "EEE dd MMM yyyy";

  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DAY = "EEE";

  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DATE2 = "dd MMM yyyy";

  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DATE3 = "dd MMM yyyy h:mm a";

  //Membership Tier - review 1.1 fix - added by webster
  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DATE4 = "EEE, dd MMM yyyy";

  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DATE5 = "dd MMMMM yyyy";

  public static final String DATE_FORMAT_DEFAULT_FOR_SHOW_DATETIME_FULL = "EEE dd MMM yyyy h:mm a";

  public static final String DATE_FORMAT_DEFAULT_FOR_RECEIPT_DATETIME = "MMM dd, HH:mm:ss a";

  public static final String
      DATE_FORMAT_DEFAULT_FOR_SHOW_DATETIME_FULL_REPORT =
      "EEE, dd MMM yyyy h:mm a";

  public static final String DATE_FORMAT_FOR_SALE = "E, dd MMM yyyy h:mm a";

  public static final String TIME_FORMAT_FOR_SALE = "h:mm a";

  public static final String DATE_FORMAT_NUMERIC_DATE_WITHOUT_TIME = "ddMMyyyy";

  public static final String DATE_FORMAT_NUMERIC_TIME = "HH:mm:ss";

  public static final String START_TIME = " 00:00";

  public static final String END_TIME = " 23:59";

  public static final String SALES_DATE_FORMAT_DEFAULT_FOR_DATE = "yyyyMMdd";

  public static final String REPORT_GEN_DATE = "yyyyMMdd";

  public static final String DATE_FORMAT_FOR_WEBSERVICE_DATE = "yyyy-MM-dd";

  public static final String DATE_FORMAT_FOR_WEBSERVICE_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

  public static final String DATE_FORMAT_FOR_BATCHPRINTJOB = "yyyyMMddHHmm";

  public static final String
      DATE_FORMAT_FOR_BATCHPRINTJOB_TRANSACTIONDETAILS =
      "E, dd MMM yyyy h:mm a";

  public static final String
      DATE_FORMAT_DEFAULT_FOR_DAY_DATE_AND_TIME_12HOUR_FORMAT =
      "EEE, dd MMM yyyy h:mm a";

  public static final String UTC = "UTC";

  public static final String GMT = "GMT";

  public static final String SINGAPORE = "SINGAPORE";

  public static final String SINGAPORE_TIME = "SINGAPORE TIME";

  public static final Date MAX_DATE = new Date(Long.MAX_VALUE);
  public static final Date MIN_DATE = new Date(0l);

  //Constants for computing Date for hour/minutes in HQL
  public static final Long HQL_DIVIDE_FOR_HOUR = new Long(24);
  public static final Long HQL_DIVIDE_FOR_MINUTE = new Long(1440);
  public static final String HQL_DATE_FORMAT = "MM/DD/YYYY HH24:MI";

  /**
   * Manual Method - YIELDS INCORRECT RESULTS - DO NOT USE*.
   * @param dateOld the date old
   * @param dateNew the date new
   * @return the difference day
   */
        /* http://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
         * This method is used to find the no of days between the given dates */
  public static Long getDifferenceDay(Date dateOld, Date dateNew) {
    if (dateNew == null) {
      return 0L;
    }
    Calendar calendarOld = Calendar.getInstance();
    Calendar calendarNew = Calendar.getInstance();
    calendarOld.setTime(dateOld);
    calendarNew.setTime(dateNew);
    long millisecondsOld = calendarOld.getTimeInMillis();
    long millisecondsNew = calendarNew.getTimeInMillis();
    long difference = millisecondsNew - millisecondsOld;
    long differenceDays = difference / (24 * 60 * 60 * 1000);
    return differenceDays;
  }

  /**
   * Days between.
   * @param startDate the start date
   * @param endDate the end date
   * @return the long
   */
  public static long daysBetween(Calendar startDate, Calendar endDate) {
    Calendar date = (Calendar) startDate.clone();
    long daysBetween = 0;
    while (date.before(endDate)) {
      date.add(Calendar.DAY_OF_MONTH, 1);
      daysBetween++;
    }
    return daysBetween;
  }

  /**
   * Months between.
   * @param startDate the start date
   * @param endDate the end date
   * @return the long
   */
  public static long monthsBetween(Date startDate, Date endDate) {
    Calendar startCalendar = new GregorianCalendar();
    startCalendar.setTime(startDate);
    Calendar endCalendar = new GregorianCalendar();
    endCalendar.setTime(endDate);

    int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
    int
        diffMonth =
        diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    return diffMonth;
  }

  /**
   * extract name of day from the given date.
   *
   * @param date the date
   * @return the day
   */
        /*public static String getDay(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("dd");
		String index = format.format(date);
		int id = Integer.parseInt(index);
		return CalendarEnum.getId(id).getDay();
	}*/

  /**
   * Format date.
   * @param formattedString the formatted string
   * @param date the date
   * @return the string
   */
  public static String formatDate(String formattedString, Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(formattedString);
    return sdf.format(date);
  }

  /**
   * Format date.
   * @param formattedString the formatted string
   * @param date the date
   * @return the date
   * @throws ParseException the parse exception
   */
  public static Date formatDate(String formattedString, String date) throws ParseException {
    return new SimpleDateFormat(formattedString).parse(date);
  }

  /**
   * Adds the days.
   * @param date the date
   * @param days the days
   * @return the date
   */
  public static Date addDays(Date date, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days); //minus number would decrement the days
    return cal.getTime();
  }

  public static Date addMonths(Date date, int months) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.MONTH, months);
    return cal.getTime();
  }

  public static Date addYears(Date date, int years) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.YEAR, years);
    return cal.getTime();
  }

  public static Date addSeconds(Date date, int seconds) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.SECOND, seconds);
    return cal.getTime();
  }


  /**
   * Retrieves a date without the time.
   * @param date the date
   * @return the date without time
   */
  public static Date getDateWithoutTime(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  /**
   * Retrieves date of the next day.
   * @param date the date
   * @return the tomorrow date
   */
  public static Date getTomorrowDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DAY_OF_MONTH, 1);
    return cal.getTime();
  }

  /**
   * Retrieves yesterday's date.
   * @param date the date
   * @return the yesterday midnight date
   */
  public static Date getYesterdayMidnightDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, 0, 0, 0);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    return cal.getTime();
  }

  /**
   * Retrieves today's date.
   * @param date the date
   * @return the today midnight date
   */
  public static Date getTodayMidnightDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, 0, 0, 0);
    return cal.getTime();
  }

  /**
   * Retrieves next date one second after this date
   * @param date the date
   * @return the next date
   */
  public static Date getNextDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.SECOND, 1);
    return cal.getTime();
  }


  /**
   * Converts the date to a calendar
   */
  public static Calendar dateToCalendar(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  /**
   * Retrieves yesterday's min datetime.
   * @return the yesterday's min datetime.
   */
  public static Date getYesterdayMinDate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * Retrieves yesterday's max datetime.
   * @return the yesterday's max datetime.
   */
  public static Date getYesterdayMaxDate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  /**
   * Retrieves today's min datetime.
   * @return the today's min datetime.
   */
  public static Date getTodayMinDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * Retrieves today's max datetime.
   * @return the today's max datetime.
   */
  public static Date getTodayMaxDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  /**
   * Retrieves last month min datetime.
   * @return last month's min datetime.
   */
  public static Date getLastMonthMinDate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -1);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * Retrieves last month max datetime.
   * @return last month max datetime.
   */
  public static Date getLastMonthMaxDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  public static String getTimeWithoutDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    return sdf.format(date);

  }
}