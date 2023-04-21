package com.example.learndemo.mq.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 生产中不建议使用事务模式，性能比较低，尽量使用确认模式
 * @Date: 2021/3/19 14:12
 */

@Component
@Slf4j
public class Receiver7 {

    @RabbitListener(queues = "testMq_mes_confirm_1")
    @RabbitHandler
    public void receiver3(String msg, Channel channel, Message message) throws IOException {

        try {
            log.info("消崽子收到消息：{}", msg);
            //TODO 具体业务
            
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }  catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                // 拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }


        System.out.println("Test4 receiver1:"+msg);
    }

    @RabbitListener(queues = "testMq_mes_confirm_2")
    @RabbitHandler
    public void receiver4(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("消崽子2收到消息：{}", msg);
            //TODO 具体业务
            int i = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }  catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                // 拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }

        System.out.println("Test4 receiver2:"+msg);
    }
}

