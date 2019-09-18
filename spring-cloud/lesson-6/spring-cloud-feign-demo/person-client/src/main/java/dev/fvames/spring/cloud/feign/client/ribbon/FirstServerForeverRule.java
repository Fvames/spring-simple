package dev.fvames.spring.cloud.feign.client.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 *
 * @version 2019/9/18 16:32
 */

public class FirstServerForeverRule extends AbstractLoadBalancerRule {
	@Override
	public void initWithNiwsConfig(IClientConfig iClientConfig) {

	}

	@Override
	public Server choose(Object o) {
		ILoadBalancer loadBalancer = getLoadBalancer();
		List<Server> allServers = loadBalancer.getAllServers();
		Server server = allServers.get(0);

		System.err.printf(">>>>> 服务端口：%s \n", server.getPort());
		return server;
	}
}
