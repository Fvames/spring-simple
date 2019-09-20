package dev.fvames.spring.cloud.stream.kafka.javaapi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Api 方式发送
 *
 * @version 2019/9/20 16:38
 */

public class ProducerApiDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Properties properties = new Properties();
		// 服务地址
		properties.setProperty("bootstrap.servers", "localhost:9092");
		// 序列化
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		// topic
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", "message-key", "message-value");

		Future<RecordMetadata> send = producer.send(producerRecord);
		// 异步执行（等待）
		RecordMetadata recordMetadata = send.get();
		System.out.printf("offset: %s, partition: %s \n", recordMetadata.offset(), recordMetadata.partition());
	}
}
