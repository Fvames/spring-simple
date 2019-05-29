package dev.fvames.messageconverteronspringmvc.http.message;

import dev.fvames.messageconverteronspringmvc.domain.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 内容处理
 *
 * @version 2019/5/29 17:51
 */

public class PropertiesPersonHttpMessageConvert extends AbstractHttpMessageConverter<Person> {

    public PropertiesPersonHttpMessageConvert() {
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream inputStream = inputMessage.getBody();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream, getDefaultCharset()));

        Person person = new Person();
        person.setId(Long.parseLong(properties.getProperty("person.id")));
        person.setName(properties.getProperty("person.name"));

        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream outputStream = outputMessage.getBody();

        Properties properties = new Properties();
        properties.setProperty("person.id", String.valueOf(person.getId()));
        properties.setProperty("person.name", person.getName());

        properties.store(new OutputStreamWriter(outputStream, getDefaultCharset()), "write by fvames server ...");

    }
}
