package dev.fvames.springjunit;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @version 2019/5/27 16:33
 */

public class Junit5Test {

    @BeforeEach
    public void prepare() throws Exception {
        System.out.printf("Junit%s 方式：%s数据源 \n", 5, "准备");
    }

    @AfterEach
    public void after() throws Exception {
        System.out.printf("Junit%s 方式：%s数据源 \n", 5, "关闭");
    }

    @Test
    public void testHelloWorld() {
        System.out.println("hello World");
    }

    @Test
    public void testHelloWorld2() {
        System.out.println("hello World2");
    }

    @RepeatedTest(value = 5)
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void test10Times(int i) {
        Assert.assertTrue(i > -1);
    }
}
