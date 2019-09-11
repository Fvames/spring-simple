package dev.fvames.rabbitmq.provider;

import dev.fvames.rabbitmq.config.Constant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 发送者
 *
 * @author op_lisj@essence.com.cn
 * @version 2019/9/10 10:21
 */
@Component
public class MyProvider {

	@Autowired
	private RabbitTemplate amqpTemplate;
	private AtomicLong firstNumber = new AtomicLong(0);
	private AtomicLong secondNumber = new AtomicLong(0);
	private AtomicLong thirdNumber = new AtomicLong(0);

	public void sendToFirst() {
		// 交换机模式（直连、Topic 主题、Fanout 广播）
		amqpTemplate.convertAndSend("", Constant.QUEUE_FIRST,
				"[ First ] Send msg-" + firstNumber.incrementAndGet() + " to first queue.");
	}

	public void sendToSecond() {
		// 交换机、路由key，msg
		amqpTemplate.convertAndSend(Constant.EXCHANAGE_TOPIC, "essen.sns",
				"[ Second ] Send msg-" + secondNumber.incrementAndGet() + " to second queue.");
	}

	public void sendToThird() {
		amqpTemplate.convertAndSend(Constant.EXCHANAGE_FANOUT, "",
				"[ Third ] Send msg-" + thirdNumber.incrementAndGet() + " to Third queue.");
	}

	public void sendToSnsServer() {
		amqpTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			System.err.println(correlationData);
			System.err.println("ack: " + ack);
			System.err.println("cause: " + cause);
		});
		// 直连模式
		amqpTemplate.convertAndSend(Constant.EXCHANGE_SNS_CARD, Constant.QUEUE_UPDATE_CARD,
				"[ updateCard ] Send msg-" + firstNumber.incrementAndGet() + " to updateCard queue.");
	}
}
