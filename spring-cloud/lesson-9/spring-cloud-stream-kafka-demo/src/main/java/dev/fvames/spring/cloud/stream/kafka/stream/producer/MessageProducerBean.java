package dev.fvames.spring.cloud.stream.kafka.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 定义消息标准发送源
 *
 * @version 2019/9/20 17:54
 */
@Component
@EnableBinding(Source.class)
public class MessageProducerBean {

	@Autowired
	private Source source;

	@Autowired
	@Qualifier(Source.OUTPUT)
	private MessageChannel messageChannel;

	public void send(String message) {
		System.out.println("message producer bean send .....");
		//messageChannel.send(MessageBuilder.withPayload(message).build());
		source.output().send(MessageBuilder.withPayload(message).build());
	}
}
