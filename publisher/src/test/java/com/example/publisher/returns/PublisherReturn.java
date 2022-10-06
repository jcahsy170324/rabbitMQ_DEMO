package com.example.publisher.returns;

import com.example.publisher.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName PublisherReturn
 * @Description TODO
 * @Author jincheng
 * @Date 2022/10/6 14:13
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class PublisherReturn {
    private Logger logger= LoggerFactory.getLogger(RabbitTemplate.class);
    @Autowired
    private RabbitTemplate template;
    @Test
    public void test1(){
        template.setMandatory(true);
        template.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                logger.info("return execute"+returnedMessage.getReplyText());

            }
        });
        template.convertAndSend(RabbitmqConfig.EXCHANGE_NAME,"boot.hahaha","test return");
        System.out.println("end");
    }
}
