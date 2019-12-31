package dev.fvames.quartz.jdktimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * jdk自带定时任务处理
 * 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
 *
 * @author
 * @version 2019/12/25 14:31
 */

public class MyJDKTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("It's JDK Task. ");
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyJDKTask(), 1000, 2000);
	}
}
