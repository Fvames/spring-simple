package dev.fvames.starterclient.jmxdemo;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @version 2019/7/12 15:58
 */

public class JMXMain {

	public static void main(String[] args) throws Exception{
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName on = new ObjectName("dev.fvames.starterclient.jmxdemo.Mechine:type=mechine");
		MechineMBean mechineMBean = new Mechine();
		mBeanServer.registerMBean(mechineMBean, on);

		System.in.read();
	}
}
