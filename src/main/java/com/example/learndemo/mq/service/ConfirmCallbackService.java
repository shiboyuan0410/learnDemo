package com.example.learndemo.mq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 10:38
 */
@Service
@Slf4j
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {

    /**
     * correlationData：对象内部只有一个 id 属性，用来表示当前消息的唯一性。
     * ack：消息投递到broker 的状态，true表示成功。
     * cause：表示投递失败的原因。
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(!ack){
            log.error("消息发送异常!");
        }else{
            log.info("发送者爸爸已经收到确认，correlationData={},ack={},cause={}",correlationData.getId(),ack,cause);
        }
    }
}
