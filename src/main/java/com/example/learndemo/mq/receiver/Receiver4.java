package com.example.learndemo.mq.receiver;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Work模式
 * 一个生产者对应多个消费者
 * 一条消息只能被一个消费者消费
 * @Author: shiboyuan
 * @Date: 2021/3/19 14:07
 */
@Component
public class Receiver4 {

    @RabbitListener(queuesToDeclare = @Queue("testMq_mes6"))
    public void receiver1(String msg){
        System.out.println("Test4 receiver1:"+msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("testMq_mes6"))
    public void receiver(String msg){
        System.out.println("Test4 receiver2:"+msg);
    }

}
