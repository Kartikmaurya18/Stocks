package com.jamesaworo.stocky.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateStringConverter implements Converter<String, LocalDate> {
	@Override
	public LocalDate convert(String source) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return ObjectUtils.isEmpty(source) ? null : LocalDate.parse(source, format);
	}
}
