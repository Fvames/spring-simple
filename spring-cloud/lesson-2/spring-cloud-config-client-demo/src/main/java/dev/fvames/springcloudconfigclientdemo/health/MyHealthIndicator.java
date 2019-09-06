package dev.fvames.springcloudconfigclientdemo.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * TODO 类描述
 *
 * @version 2019/9/6 14:54
 */

public class MyHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		builder.down().withDetail("MyHealthIndicator", "Down Test");
	}
}
