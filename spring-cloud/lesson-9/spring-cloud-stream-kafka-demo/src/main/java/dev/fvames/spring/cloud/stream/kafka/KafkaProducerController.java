package dev.fvames.spring.cloud.stream.kafka;

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

	public KafkaProducerController(@Value("${kafka.topic}") String topic, KafkaTemplate<String, String> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping("/message/send")
	public Boolean messageSend(@RequestParam String message) {
		kafkaTemplate.send(topic, message);

		return true;
	}
}
