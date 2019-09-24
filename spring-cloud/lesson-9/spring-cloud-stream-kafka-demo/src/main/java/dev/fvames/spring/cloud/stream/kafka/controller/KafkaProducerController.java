package dev.fvames.spring.cloud.stream.kafka.controller;

import dev.fvames.spring.cloud.stream.kafka.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @version 2019/9/20 18:01
 */
@RestController
public class KafkaProducerController {

	private final String topic;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final MessageProducerBean producerBean;

	public KafkaProducerController(@Value("${kafka.topic}") String topic, KafkaTemplate<String, String> kafkaTemplate, MessageProducerBean producerBean) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
		this.producerBean = producerBean;
	}

	@PostMapping("/kafka/message/send")
	public Boolean kafkaMessageSend(@RequestParam String message) {
		kafkaTemplate.send(topic, message);

		return true;
	}

	@PostMapping("/stream/message/send")
	public Boolean messageSend(@RequestParam String message) {
		producerBean.send(message);

		return true;
	}
}
