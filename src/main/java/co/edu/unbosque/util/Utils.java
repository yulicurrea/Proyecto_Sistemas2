package co.edu.unbosque.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

	public static void restarFechas() {
		Calendar c = Calendar.getInstance();

		System.out.println("Fecha original: " + formatearCalendar(c));
		// Restar cinco meses
		c.add(Calendar.MONTH, -5);
		System.out.println("-5 meses: " + formatearCalendar(c));
	}

	public static int obtenerEdad(Date fechaNacimiento) {
		Date date = fechaNacimiento;
		// Converting obtained Date object to LocalDate object
		Instant instant = date.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate givenDate = zone.toLocalDate();
		// Calculating the difference between given date to current date.
		Period period = Period.between(givenDate, LocalDate.now());
		return period.getYears();
	}

	// Este es un método para formatear e imprimir la fecha, pero no tiene nada
	// que ver con sumar o restar
	public static String formatearCalendar(Calendar c) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
		return df.format(c.getTime());
	}
	
	public static String dateToPattern() {
		String pattern = "dd-MM-yyyy HH:mm:ss";
		
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}
}
