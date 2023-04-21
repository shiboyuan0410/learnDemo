package com.example.learndemo.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/19 14:07
 */
@Component
public class Receiver1 {

    @RabbitListener(queues = "testMq_mes1")
    public String receiver1(String msg){
        System.out.println("Test1 receiver1:"+msg);
        return "Test1 receiver1:"+msg;
    }

    @RabbitListener(queues = "testMq_mes2")
    public String receiver2(String msg){
        System.out.println("Test1 receiver2:"+msg);
        return "Test2 receiver1:"+msg;
    }

}
