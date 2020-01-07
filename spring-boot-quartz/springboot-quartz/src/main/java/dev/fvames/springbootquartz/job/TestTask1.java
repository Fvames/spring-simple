package dev.fvames.springbootquartz.job;

import dev.fvames.springbootquartz.service.SysJobService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@DisallowConcurrentExecution
public class TestTask1 implements Job {
	@Autowired
	private SysJobService sysJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("jobcount: "+ sysJobService.getJobCount());
        System.out.println(Thread.currentThread().getName() + " " + LocalDateTime.now() + " Task1 ");
    }
}
