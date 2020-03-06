package com.parameta.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validaciones {

	public boolean validarFecha(String fecha, String formatoFecha) {
		if (fecha == null) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		sdf.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			Date date = sdf.parse(fecha);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean validarDouble(String n) {
		try {
			Double.parseDouble(n);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int calcularEdad(Date fechaNac) {
		Calendar cal = Calendar.getInstance(); // current date
		int anoActual = cal.get(Calendar.YEAR);
		int mesActual = cal.get(Calendar.MONTH);
		int currDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(fechaNac); // now born date
		int annos = anoActual - cal.get(Calendar.YEAR);
		int bornMonth = cal.get(Calendar.MONTH);
		if (bornMonth == mesActual) { // same month
			return cal.get(Calendar.DAY_OF_MONTH) <= currDay ? annos : annos - 1;
		} else {
			return bornMonth < mesActual ? annos - 1 : annos;
		}
	}
}
