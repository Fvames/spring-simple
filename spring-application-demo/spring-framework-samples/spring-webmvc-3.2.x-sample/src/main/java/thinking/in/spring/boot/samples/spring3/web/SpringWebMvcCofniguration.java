package thinking.in.spring.boot.samples.spring3.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @version 2019/10/18 10:04
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "thinking.in.spring.boot.samples.spring3.web.controller")
public class SpringWebMvcCofniguration {
}
