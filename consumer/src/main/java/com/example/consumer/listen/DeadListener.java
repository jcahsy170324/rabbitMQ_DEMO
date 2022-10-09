package com.example.consumer.listen;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName DeadListen
 * @Description TODO
 * @Author jincheng
 * @Date 2022/10/8 8:51
 * @Version 1.0
 **/
@Component
public class DeadListener {

    @RabbitListener(queues = "normal_queue")
    public void consume(String msg) throws IOException {
        System.out.println("接收到normal队列的消息：" + msg);
    }
}
