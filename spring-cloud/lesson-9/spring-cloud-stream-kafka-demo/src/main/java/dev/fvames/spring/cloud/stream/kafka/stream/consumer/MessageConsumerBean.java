package dev.fvames.spring.cloud.stream.kafka.stream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @version 2019/9/24 17:13
 */
@Component
@EnableBinding(Sink.class)
public class MessageConsumerBean {

	@Autowired
	private Sink sink;

	@StreamListener(Sink.INPUT)
	public void onStreamListener(Object message) {
		System.out.println("@StreamListener：" + message);
	}
}
