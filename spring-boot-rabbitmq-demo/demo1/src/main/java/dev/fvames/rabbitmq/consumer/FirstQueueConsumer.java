package dev.fvames.rabbitmq.consumer;

import dev.fvames.rabbitmq.config.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author op_lisj@essence.com.cn
 * @version 2019/9/10 10:26
 */

@Component
public class FirstQueueConsumer {

	@RabbitListener(queues = Constant.QUEUE_FIRST)
	public void processFirst(String msg) {
		System.out.printf("First Queue received msg context: %s \n", msg);
	}

	@RabbitListener(queues = Constant.QUEUE_SECOND)
	public void processSecond(String msg) {
		System.out.printf("Second Queue received msg context: %s \n", msg);
	}

	@RabbitListener(queues = Constant.QUEUE_THIRD)
	public void processThird(String msg) {
		System.out.printf("Third Queue received msg context: %s \n", msg);
	}

}
