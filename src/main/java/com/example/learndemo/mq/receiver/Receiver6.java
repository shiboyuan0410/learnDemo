package com.example.learndemo.mq.receiver;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅模型-Topic 主题模式
 * # 匹配一个或者多个
 * * 匹配一个
 * @Date: 2021/3/19 14:12
 */

@Component
public class Receiver6 {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "testMq_mes_topic_1",
                    durable ="true"
            ),
            exchange = @Exchange(
                    value = "topicExchange",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"
            ),
            key = {"topic.*"}
    ))
    public void receiver1(String msg){
        System.out.println("Test4 receiver1:"+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "testMq_mes_topic_2",
                    durable ="true"
            ),
            exchange = @Exchange(
                    value = "topicExchange",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"
            ),
            key = {"topic.#"}
    ))
    public void receiver2(String msg){
        System.out.println("Test4 receiver2:"+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "testMq_mes_topic_3",
                    durable ="true"
            ),
            exchange = @Exchange(
                    value = "topicExchange",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"
            ),
            key = {"update.#"}
    ))
    public void receiver3(String msg){
        System.out.println("Test4 receiver3:"+msg);
    }
}

