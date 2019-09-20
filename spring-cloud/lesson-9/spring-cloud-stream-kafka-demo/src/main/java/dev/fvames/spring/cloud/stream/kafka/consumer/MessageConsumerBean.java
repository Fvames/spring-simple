package dev.fvames.spring.cloud.stream.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费 bean
 *
 * @version 2019/9/20 18:00
 */
@Component
public class MessageConsumerBean {

	@KafkaListener(topics = "${kafka.topic}")
	public void onMessage(String message) {

		System.out.println("Kafka 消费者监听器，接受到消息：" + message);
	}
}
