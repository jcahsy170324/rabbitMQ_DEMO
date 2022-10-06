package com.example.publisher.confirms;

import com.example.publisher.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName Publisher
 * @Description TODO
 * @Author jincheng
 * @Date 2022/10/6 13:39
 * @Version 1.0
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class Publisher {
    private Logger logger= LoggerFactory.getLogger(RabbitTemplate.class);
    @Autowired
    private RabbitTemplate template;

    @Test
    public void test1(){
        //开启confirms
        //设置confirms的异步回调
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack){
                    logger.info("send msg success hahaha");
                }else {
                    logger.info("send msg fail");
                    logger.info("fail reason is:"+cause);
                }
            }
        });
        template.convertAndSend(RabbitmqConfig.EXCHANGE_NAME,"boot.hahaha","boot mq hello");
        System.out.println("end");
    }
}
