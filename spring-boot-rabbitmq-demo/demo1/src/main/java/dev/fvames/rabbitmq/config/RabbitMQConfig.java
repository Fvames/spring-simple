package dev.fvames.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQ 配置类
 *
 * @version 2019/9/10 10:01
 */
@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue getFristQueue() {
		// 参数：队列名称、是否持久化，是否唯一，是否自动删除，配置参数（过期时间等设置）
		return new Queue(Constant.QUEUE_FIRST, true, false, false, null);
	}

	@Bean("secondQueue")
	public Queue getSecondQueue() {
		// 默认持久化
		return new Queue(Constant.QUEUE_SECOND);
	}

	@Bean("thirdQueue")
	public Queue getThirdQueue() {
		return new Queue(Constant.QUEUE_THIRD);
	}

	@Bean("topicExchange")
	public TopicExchange getTopicExchange() {
		return new TopicExchange(Constant.EXCHANAGE_TOPIC);
	}

	@Bean("fanoutExchange")
	public FanoutExchange getFanoutExchange() {
		return new FanoutExchange(Constant.EXCHANAGE_FANOUT);
	}

	/**
	 * 绑定 exchange 交换机和 queue 通道
	 * <ul>
	 *     <li><code>#</code>： 匹配 0 个或多个单词</li>
	 *     <li><code>*</code>： 匹配 1 单词</li>
	 * </ul>
 	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding buildSecond(@Qualifier("secondQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("#.sns.#");
	}

	@Bean
	public Binding buildThird(@Qualifier("thirdQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setHost("localhost");
		cachingConnectionFactory.setPublisherConfirms(true);
		return cachingConnectionFactory;
	}

	/*@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}*/

	//@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		/*rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			public void returnedMessage(Message message,
										int replyCode,
										String replyText,
										String exchange,
										String routingKey) {
				System.out.println("回发的消息：");
				System.out.println("replyCode: " + replyCode);
				System.out.println("replyText: " + replyText);
				System.out.println("exchange: " + exchange);
				System.out.println("routingKey: " + routingKey);
			}
		});

		rabbitTemplate.setChannelTransacted(true);*/

		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if (!ack) {
					System.out.println("发送消息失败：" + cause);
					throw new RuntimeException("发送异常：" + cause);
				} else {
					System.out.println("发送消息成功：" + cause);
				}
			}
		});


		return rabbitTemplate;
	}
}
