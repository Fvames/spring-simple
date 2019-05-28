package dev.fvames.springJunit;

import dev.fvames.springJunit.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Person.class)
public class SpringJunitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJunitApplication.class, args);
    }

}
