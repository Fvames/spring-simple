package dev.fvames.rabbitmq;

import dev.fvames.rabbitmq.provider.MyProvider;
import dev.fvames.rabbitmq.provider.TopicProvider;
import dev.fvames.rabbitmq.provider.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 测试
 *
 * @version 2019/9/10 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitMQDemoApplicationTest {

	@Autowired
	private MyProvider myProvider;
	@Autowired
	private TopicProvider topicProvider;

	@Test
	public void firstQueueSend() {
		myProvider.sendToFirst();
		//myProvider.sendToSecond();
		//myProvider.sendToThird();

		//myProvider.sendToSnsServer();
	}

	@Test
	public void sendToTopic() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setAge(23);
		user.setUserName("张三");

		topicProvider.sendToTopicCard(user);

		TimeUnit.SECONDS.sleep(10);
	}
}
