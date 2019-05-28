package dev.fvames.springJunit.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 参数
 *
 * @version 2019/5/28 16:41
 */
//@Component(value = "person")
@ConfigurationProperties(prefix = "person")
public class Person {
    private Long id;
    //@Value("${person.name}")
    private String name;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
