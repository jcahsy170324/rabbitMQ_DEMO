package com.example.consumer.listen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyListen
 * @Description TODO
 * @Author jincheng
 * @Date 2022/10/6 130
 * @Version 1.0
 **/
@Component
public class MyListen {
    //listen a queue
    @RabbitListener(queues = "boot_queue")
    public void myListen1(String msg) {
        System.out.println("监听到的消息是:"+msg);
    }
}
