package dev.fvames.springbootquartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * description: quartz测试任务
 */
@Component
@EnableScheduling
public class TestJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("quartz test job started.");
        LOGGER.info("quartz test job finished.");
    }
}