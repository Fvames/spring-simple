package dev.fvames.quartz;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CalendarIntervalTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * TODO 类描述
 *
 * @author
 * @version 2019/12/27 13:41
 */

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("first quartz job.");
	}


	public static void main(String[] args) throws Exception{
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("job1", "job-group1")
				.withDescription("这是第一个 quartz job。")
				.usingJobData("name", "quartz Demo1")
				.build();


		/*SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "trigger-group1")
				.withDescription("这是第一个 quartz trigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();*/

		CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
				.withInterval(3, DateBuilder.IntervalUnit.SECOND);

		CalendarIntervalTrigger calendarIntervalTrigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "trigger-group1")
				.withDescription("这是第一个 quartz trigger")
				//.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.withSchedule(calendarIntervalScheduleBuilder)
				.build();

		StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = stdSchedulerFactory.getScheduler();
		scheduler.scheduleJob(jobDetail, calendarIntervalTrigger);
		// 绑定监听器
		scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

		scheduler.start();

		//TimeUnit.SECONDS.sleep(3);
		//
		//scheduler.pauseJob(new JobKey("job1", "job-group1"));
		//scheduler.start();
	}

	static class MyJobListener implements JobListener{

		@Override
		public String getName() {
			return "first job listener";
		}

		@Override
		public void jobToBeExecuted(JobExecutionContext context) {
			System.out.printf("context: %s, jobkey: %s \n", context, context.getJobDetail().getKey());
			System.out.println(">>>>>>>>>>>>>>>>>> jobToBeExecuted");
		}

		@Override
		public void jobExecutionVetoed(JobExecutionContext context) {

			System.out.println(">>>>>>>>>>>>> jobExecutionVetoed");
		}

		@Override
		public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

			System.out.println(">>>>>>> jobWasExecuted");
		}
	}
}
