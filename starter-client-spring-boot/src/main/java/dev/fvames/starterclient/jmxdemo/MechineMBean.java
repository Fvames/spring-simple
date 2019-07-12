package dev.fvames.starterclient.jmxdemo;

/**
 *
 * @version 2019/7/12 15:54
 */

public interface MechineMBean {


	int getCpuCodeNum();

	long getFreeMemory();

	void shutdown();
}
