package com.example.learndemo.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅模型-fanout 扇形 也称为广播
 * 在广播模式下，消息发送流程是这样的：
 * 可以有多个消费者
 * 每个消费者有自己的queue（队列）
 * 每个队列都要绑定到Exchange（交换机）
 * 生产者发送的消息，只能发送到交换机，交换机来决定要发给哪个队列，生产者无法决定。
 * 交换机把消息发送给绑定过的所有队列
 * 队列的消费者都能拿到消息。实现一条消息被多个消费者消费
 * @Author: shiboyuan
 * @Date: 2021/3/19 14:12
 */

@Component
public class Receiver3 {

    @RabbitListener(queues = "testMq_mes_fanout_1")
    public void receiver1(String msg){
        System.out.println("Test3 receiver1:"+msg);
    }

    @RabbitListener(queues = "testMq_mes_fanout_2")
    public void receiver2(String msg){
        System.out.println("Test3 receiver2:"+msg);
    }

}

