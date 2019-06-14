package dev.fvames.springbootquartz;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * description: quartz定时任务配置
 */
@Configuration
//@Profile({"stg-job", "prd"})
@SuppressWarnings("unused")
public class QuartzConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzConfig.class);
    private static final String RESOURCE_NAME = "quartz.properties";

    @Resource
    private DataSource dataSource;

    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger... triggers) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream(RESOURCE_NAME));
        } catch (IOException e) {
            LOGGER.error("quartz定时任务加载{}过程中发生错误。");
            throw new Error(e);
        }
        factory.setQuartzProperties(p);
        factory.setDataSource(dataSource);
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(20);
        factory.setTriggers(triggers);
        factory.setJobFactory(springJobFactory);
        return factory;
    }
}