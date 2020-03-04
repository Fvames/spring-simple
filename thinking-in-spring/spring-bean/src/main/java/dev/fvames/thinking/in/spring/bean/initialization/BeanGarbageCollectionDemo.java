package dev.fvames.thinking.in.spring.bean.initialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationAndDestroyDemo.class);

        applicationContext.refresh();

        applicationContext.close();
        Thread.sleep(5000L);

        System.gc();

        Thread.sleep(5000L);
    }
}
