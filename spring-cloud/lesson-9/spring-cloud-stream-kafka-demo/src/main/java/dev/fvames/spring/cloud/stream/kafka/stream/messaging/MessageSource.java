package dev.fvames.spring.cloud.stream.kafka.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @version 2019/9/24 17:37
 */

public interface MessageSource {
	String OUTPUT = "lj";

	@Output(OUTPUT)
	MessageChannel lj();
}
