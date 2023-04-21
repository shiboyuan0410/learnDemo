package com.example.learndemo.mq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 10:42
 */
@Service
@Slf4j
public class ReturnCallbackService implements RabbitTemplate.ReturnCallback{

    /**
     * 参数message（消息体）
     * replyCode（响应code）
     * replyText（响应内容）
     * exchange（交换机）
     * routingKey（队列）
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("returnedMessage ===> replyCode={} ,replyText={} ,exchange={} ,routingKey={}", replyCode, replyText, exchange, routingKey);
    }
}
