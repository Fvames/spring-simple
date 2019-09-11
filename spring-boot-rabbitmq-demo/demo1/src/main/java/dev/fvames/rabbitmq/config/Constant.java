package dev.fvames.rabbitmq.config;

/**
 * 常量
 *
 * @author op_lisj@essence.com.cn
 * @version 2019/9/10 10:23
 */

public class Constant {

	public static final String QUEUE_FIRST = "FIRST_QUEUE";
	public static final String QUEUE_SECOND = "SECOND_QUEUE";
	public static final String QUEUE_THIRD = "THIRD_QUEUE";

	// exchange
	public static final String EXCHANAGE_TOPIC = "TOPIC_EXCHANGE";
	public static final String EXCHANAGE_FANOUT = "FANOUT_EXCHANGE";

	// ------------------
	public static final String QUEUE_UPDATE_CARD = "updateCard";
	public static final String EXCHANGE_SNS_CARD = "iop.card.sns";
	public static final String ROUTING_KEY = "camp.card.sns";
}
