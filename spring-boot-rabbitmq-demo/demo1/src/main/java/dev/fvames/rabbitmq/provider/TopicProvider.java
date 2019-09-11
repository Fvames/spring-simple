package dev.fvames.rabbitmq.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * TODO 类描述
 *
 * @author op_lisj@essence.com.cn
 * @version 2019/9/11 11:06
 */
@Component
@PropertySource("classpath:rabbitmq.properties")
public class TopicProvider {

	@Value("${sns.topic.exchange}")
	private String snsTopicExchange;
	@Value("${sns.topic.routing-key}")
	private String snsTopicRoutingKey;

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * 发送社区话题卡片数据到互联网运营平台
	 *
	 * @param user 话题卡片数据对象 todo 创建 pojo
	 */
	public void sendToTopicCard(User  user) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String msgJson = mapper.writeValueAsString(user);
		amqpTemplate.convertAndSend(snsTopicExchange, snsTopicRoutingKey, msgJson);
	}
}
