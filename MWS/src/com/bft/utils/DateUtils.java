package com.bft.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.widget.ImageView;

public class DateUtils {
    
	
	public Long dateToTimestamp(String dateStr) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = null;
		try {
			date = (Date)formatter.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Calendar c = Calendar.getInstance();
	    c.setTime(date);	
	
		return c.getTimeInMillis()/1000;
    }
	
}
