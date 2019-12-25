package dev.fvames.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 排除指定日期的
 *
 * @author
 * @version 2019/12/30 10:36
 */

public class CalendarDemo {

	public static void main(String[] args) throws SchedulerException {
		// schdeule add
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		// 日历设置
		AnnualCalendar holidays = new AnnualCalendar();
		Calendar cny = new GregorianCalendar(2020, 1, 24);
		holidays.setDayExcluded(cny, true);
		Calendar christmasDay = new GregorianCalendar(2019, 12, 25);
		holidays.setDayExcluded(christmasDay, true);

		scheduler.addCalendar("holidays", holidays, false, false);

		// jobDetail
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("job1", "job1-group")
				.usingJobData("key", "calendar")
				.withDescription("排除指定日期的 job")
				.build();

		// triger add calendar
		SimpleTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "trigger1-group")
				.modifiedByCalendar("holidays")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}

}
