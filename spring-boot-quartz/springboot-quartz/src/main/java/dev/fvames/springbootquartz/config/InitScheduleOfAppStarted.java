package dev.fvames.springbootquartz.config;

import dev.fvames.springbootquartz.entity.SysJob;
import dev.fvames.springbootquartz.service.SysJobService;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用启动成功后开启任务
 *
 * @author
 * @version 2020/1/2 17:27
 */
@Component
public class InitScheduleOfAppStarted implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(InitScheduleOfAppStarted.class);

	@Autowired
	private SysJobService sysJobService;
	@Autowired
	private SpringJobFactory springJobFactory;

	@Override
	public void run(String... args) throws Exception {
		Map<String, String> runJob = new HashMap();
		runJob.put("jobStatus", "1");
		List<SysJob> jobList = sysJobService.querySysJobList(runJob);

		if (null == jobList || jobList.size() <= 0) {
			LOGGER.info("app started，暂无需要执行的任务");
		}

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		// 注入 spring 示例，否则执行时找不到 spring 容器中的 bean
		scheduler.setJobFactory(springJobFactory);
		scheduler.start();

		for (SysJob sysJob : jobList) {

			// jobDetail
			String jobName = sysJob.getJobName();
			String jobGroup = sysJob.getJobGroup();
			JobDetail jobDetail = JobBuilder.newJob(getClass(sysJob.getJobClassPath()).getClass())
					.withIdentity(jobName, jobGroup)
					.build();
			// jobTriller
			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
					.withIdentity(jobName, jobGroup)
					.withSchedule(CronScheduleBuilder.cronSchedule(sysJob.getJobCron()))
					.build();
			// 关联
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
	}

	private static Job getClass(String jobClassPath) throws Exception{
		Class<?> aClass = Class.forName(jobClassPath);
		return (Job) aClass.newInstance();
	}
}
