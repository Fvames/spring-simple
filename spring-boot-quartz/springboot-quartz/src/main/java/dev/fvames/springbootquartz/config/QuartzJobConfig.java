package dev.fvames.springbootquartz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 配置定时任务
 */
//@Configuration
//@Profile({"stg-job", "prd"})
@SuppressWarnings("unused")
public class QuartzJobConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobConfig.class);

    /*@Bean
    public JobDetailFactoryBean testJobDetailFactory() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setName("test-job");
        jobDetailFactory.setGroup("sns");
        jobDetailFactory.setJobClass(TestJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public CronTriggerFactoryBean testJobTrigger(
            @Qualifier("testJobDetailFactory") JobDetailFactoryBean factory) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        if (factory.getObject() != null) {
            tigger.setJobDetail(factory.getObject());
        }
        tigger.setCronExpression("0/10 * * * * ?");
        tigger.setName("test-job-trigger");
        return tigger;
    }*/

}