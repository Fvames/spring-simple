package dev.fvames.springbootquartz.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quartz 工具类，增删改 job、trigger
 *
 * @author
 * @version 2019/12/31 16:20
 */

public class SchedulerUtil {

	public void addJob(String jobClassName, String jobName, String jobGroupName,
					   String triggerName, String triggerGroupName, String cronExpression) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		scheduler.start();

		Class<?> aClass = Class.forName(jobClassName);
		Job job = (Job) aClass.newInstance();

		JobDetail jobDetail = JobBuilder.newJob(job.getClass())
				.withIdentity(jobName, jobGroupName)
				.build();

		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
				.withIdentity(triggerName, triggerGroupName)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();

		scheduler.scheduleJob(jobDetail, cronTrigger);
	}

	public void jobPause(String jobName, String jobGroupName) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
	}

	public void jobResume(String jobName, String jobGroupName) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
	}

	public void jobDelete(String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		scheduler.pauseTrigger(triggerKey);
		scheduler.unscheduleJob(triggerKey);
		scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
	}

	public void jobReschedule(String jobName, String jobGroupName, String triggerName, String triggerGroupName
			, String cronExpression) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}
}
