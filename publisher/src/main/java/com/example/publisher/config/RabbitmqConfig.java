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

}
