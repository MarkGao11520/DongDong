package com.zrkj.ecp.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
	private String datePattern;
	
	public StringToDateConverter(String datePattern) {
		// TODO Auto-generated constructor stub
		this.datePattern = datePattern;
		System.out.println("instantiating ...... converter with pattern1:*"+datePattern);
	}

	@Override
	public Date convert(String s) {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
			dateFormat.setLenient(false);
			return dateFormat.parse(s);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new IllegalArgumentException("invalid date format. Please use this pattern\""+datePattern+"\"");
		}
	}

}
