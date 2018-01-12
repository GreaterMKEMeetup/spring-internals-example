package com.mkejug.spring.messageconverter;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;

public class PipeDelimiterHttpMessageConverter extends AbstractHttpMessageConverter {

    private static final MediaType PIPE_MESSAGE = MediaType.valueOf("text/pipe");

    public PipeDelimiterHttpMessageConverter() {
        super(PIPE_MESSAGE);
    }

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        StringBuilder builder = new StringBuilder();
        ReflectionUtils.doWithFields(o.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                builder.append("|");
                builder.append(field.get(o).toString());
            }
        });
        builder.append("\n");
        IOUtils.copy(new StringReader(builder.toString()), outputMessage.getBody());
    }
}
