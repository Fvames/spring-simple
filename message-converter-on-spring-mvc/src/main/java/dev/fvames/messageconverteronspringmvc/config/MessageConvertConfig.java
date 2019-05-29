package dev.fvames.messageconverteronspringmvc.config;

import dev.fvames.messageconverteronspringmvc.http.message.PropertiesPersonHttpMessageConvert;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @version 2019/5/29 17:46
 */
@Configuration
public class MessageConvertConfig implements WebMvcConfigurer {

    /**
     * 覆盖
     *
     * @param converters
     */
    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(new PropertiesPersonHttpMessageConvert());
        //converters.add(0, new PropertiesPersonHttpMessageConvert());

    }*/
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PropertiesPersonHttpMessageConvert());
    }
}
