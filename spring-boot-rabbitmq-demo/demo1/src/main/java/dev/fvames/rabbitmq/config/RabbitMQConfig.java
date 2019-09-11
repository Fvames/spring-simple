package dev.fvames.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQ 配置类
 *
 * @author op_lisj@essence.com.cn
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

}
