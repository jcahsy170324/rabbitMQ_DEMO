package com.example.publisher.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitmqConfig
 * @Description TODO
 * @Author jincheng
 * @Date 2022/10/5 22:15
 * @Version 1.0
 **/
@Configuration
public class RabbitmqConfig {
    //define exchange name
    public static final String EXCHANGE_NAME="boot_topic_exchange";
    //define queue name
    public static final String QUEUE_NAME = "boot_queue";
    public static final String NORMAL_EXCHANGE = "normal_exchange" ;
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String NORMAL_ROUTING_KEY = "normal.#";

    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String DEAD_QUEUE = "dead_queue";
    public static final String DEAD_ROUTING_KEY = "dead.#";

    //exchange
    @Bean("bootExchange")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //queue
    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    //bind queue to exchange
    @Bean
    public Binding getBinding(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

    @Bean(NORMAL_EXCHANGE)
    public Exchange normalExchange(){
        return ExchangeBuilder.topicExchange(NORMAL_EXCHANGE).build();
    }

    @Bean(NORMAL_QUEUE)
    public Queue normalQueue(){
        return QueueBuilder.durable(NORMAL_QUEUE).deadLetterExchange(DEAD_EXCHANGE).deadLetterRoutingKey(DEAD_ROUTING_KEY).build();
    }

    @Bean
    public Binding normalBinding(@Qualifier(NORMAL_EXCHANGE) Exchange normalExchange,@Qualifier(NORMAL_QUEUE) Queue normalQueue){
        return BindingBuilder.bind(normalQueue).to(normalExchange).with(NORMAL_ROUTING_KEY).noargs();
    }

    @Bean(DEAD_EXCHANGE)
    public Exchange deadExchange(){
        return ExchangeBuilder.topicExchange(DEAD_EXCHANGE).build();
    }

    @Bean(DEAD_QUEUE)
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    @Bean
    public Binding deadBinding(@Qualifier(DEAD_EXCHANGE) Exchange deadExchange,@Qualifier(DEAD_QUEUE) Queue deadQueue){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DEAD_ROUTING_KEY).noargs();
    }

}
