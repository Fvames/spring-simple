package dev.fvames.springjunit;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @version 2019/5/27 16:33
 */

public class Junit3Test extends TestCase {

    @Override
    protected void setUp() throws Exception {
        System.out.printf("Junit%s 方式：%s数据源 \n", 3, "准备");
    }

    @Override
    protected void tearDown() throws Exception {
        System.out.printf("Junit%s 方式：%s数据源 \n", 3, "关闭");
    }

    @Test
    public void testHelloWorld() {
        System.out.println("hello World");
    }
}
