package com.test.config;

import java.text.SimpleDateFormat;

public class DateFormats {
	
	public static SimpleDateFormat ddMMMyyyy()
	{
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
		return df;
	}
	
	public static SimpleDateFormat ddMMM()
	{
		SimpleDateFormat df = new SimpleDateFormat("dd MMM");
		return df;
	}

}
