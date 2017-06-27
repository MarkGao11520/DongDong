package com.zrkj.ecp.converter;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/2/7.
 */
public class DateConverter extends AbstractHttpMessageConverter<String> {
    private String datePattern;

    public DateConverter(String datePattern) {
        this.datePattern = datePattern;
        System.out.println("instantiating ...... converter with pattern1:*"+datePattern);
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        return String.class.isAssignableFrom(aClass);
    }

    @Override
    protected String readInternal(Class<? extends String> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(String s, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
