package eu.ark.creditark.services.creditarkservices.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;


public class DateUtils {

	private static final String localTimezone = ZoneId.systemDefault().getId();

	public static Date formatDate(final Date source) throws ParseException {
		return stringToDate((new SimpleDateFormat(GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue())).format(source),
				GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue());
	}

	public static Date stringToDate(final String source, final String format) throws ParseException {
		return (new SimpleDateFormat(format)).parse(source);
	}

	public static Date formatDate(final Date source, String format) throws ParseException {
		return stringToDate((new SimpleDateFormat(format)).format(source), format);
	}

	public static String dateToString(final Date date, final String format) throws ParseException {
		return null==date || null==format?"":(new SimpleDateFormat(format)).format(DateUtils.formatDate(
				new Date(date.getTime()),format
		));
	}
	
	public static String dateToString(final Date date) {
		if(null==date) return "";
		return (new SimpleDateFormat(GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue())).format(date);
	}
	
	public static Calendar dateToCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal;
	}
	
	public static Calendar increamentDateByDay(Calendar date, int plus) {
		date.add(Calendar.DAY_OF_MONTH, plus);
		return date;
	}
	
	
	public static int calculateTimezoneDiff(String other, int specificTimeOfDay) {
		 LocalDateTime dt = LocalDateTime.now();
		 ZonedDateTime fromZonedDateTime = dt.atZone(ZoneId.of(localTimezone));
	     ZonedDateTime toZonedDateTime = dt.atZone(ZoneId.of(other));
	     int diff = (int)(Duration.between(fromZonedDateTime, toZonedDateTime).toMillis()/3600000);
	     return LocalDateTime.now().withHour(specificTimeOfDay).getHour() - LocalDateTime.now().getHour()+diff ;
	}

	
	
}
