package dev.fvames.springjunit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @version 2019/5/27 16:33
 */

public class Junit4Test {

    @Before
    public void prepare() throws Exception {
        System.out.printf("Junit%s 方式：%s数据源 \n", 4, "准备");
    }

    @After
    public void after() throws Exception {
        System.out.printf("Junit%s 方式：%s数据源 \n", 4, "关闭");
    }

    @Test
    public void testHelloWorld() {
        System.out.println("hello World");
    }

    @Test
    public void test10Times() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + i);
            Assert.assertTrue(i > -1);
        }
    }
}
