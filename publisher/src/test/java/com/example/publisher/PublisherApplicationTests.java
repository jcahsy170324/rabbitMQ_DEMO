package com.example.publisher;

import com.example.publisher.config.RabbitmqConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class PublisherApplicationTests {
    @Autowired
    private RabbitTemplate template;
    @Test
    void contextLoads() {
        System.out.println(template);
        System.out.println(template.getUUID());
    }

    @Test
    public void test1(){
        template.convertAndSend(RabbitmqConfig.EXCHANGE_NAME,"boot.hahaha","boot mq hello");
        System.out.println("send success!");
    }
}
