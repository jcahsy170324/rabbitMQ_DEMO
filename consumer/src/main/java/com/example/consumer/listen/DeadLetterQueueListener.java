package com.example.consumer.listen;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName DeadLetterQueueListener
 * @Description TODO
 * @Author jincheng
 * @Date 2022/10/9 11:38
 * @Version 1.0
 **/
@Slf4j
@Component
public class DeadLetterQueueListener {
    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }
}
