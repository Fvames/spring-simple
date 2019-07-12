package dev.fvames.starterclient.jmxdemo;

/**
 * @version 2019/7/12 15:55
 */

public class Mechine implements MechineMBean{


	public int getCpuCodeNum() {
		return Runtime.getRuntime().availableProcessors();
	}

	public long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	public void shutdown() {
		System.exit(1);
	}
}
