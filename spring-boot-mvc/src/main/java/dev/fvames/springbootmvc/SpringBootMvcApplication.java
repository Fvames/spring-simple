package dev.fvames.springbootmvc;

import dev.fvames.springbootmvc.interceptor.DefaultHanlderInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@SpringBootApplication
@EnableWebMvc
public class SpringBootMvcApplication /*extends WebMvcConfigurerAdapter*/ implements WebMvcConfigurer, ErrorPageRegistrar {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultHanlderInterceptor());
    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("/");
        resolver.setSuffix(".fmk");
        resolver.setOrder(1);     // 多模板引擎且采用 Java 配置模式时，不设置最高级无法查找到文件

        return resolver;
    }

    @Bean
    FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/freemarker");
        return configurer;
    }


}
