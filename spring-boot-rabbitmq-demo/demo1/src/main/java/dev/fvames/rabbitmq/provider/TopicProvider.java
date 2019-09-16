package dev.fvames.rabbitmq.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TODO 类描述
 *
 * @version 2019/9/11 11:06
 */
@Service
public class TopicProvider {

	@Value("${sns.topic.exchange}")
	private String snsTopicExchange;
	@Value("${sns.topic.routing-key}")
	private String snsTopicRoutingKey;

	@Autowired
	private RabbitTemplate amqpTemplate;

	public void sendToTopicCard(User  user) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String msgJson = mapper.writeValueAsString(user);
		amqpTemplate.convertAndSend(snsTopicExchange, snsTopicRoutingKey, msgJson);
	}
}
