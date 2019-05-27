package dev.fvames.springjunit;

import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.env.MockEnvironment;

/**
 * @version 2019/5/27 17:36
 */

public class EnvironmentTest {

    @Test
    public void testStandardEnvironment() {
        StandardEnvironment environment = new StandardEnvironment();
        System.out.println(environment.getProperty("Path"));
    }

    @Test
    public void testGetProperty() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("os.name", "windos 7");

        System.out.println(environment.getProperty("os.name"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getenv());
    }

}
