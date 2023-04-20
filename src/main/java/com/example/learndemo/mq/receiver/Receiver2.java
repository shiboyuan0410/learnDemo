package com.example.learndemo.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
 * @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，
 * 具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
 * @Author: shiboyuan
 * @Date: 2021/3/19 14:12
 */

@Component
@RabbitListener(queues = "testMq_mes3")
public class Receiver2 {

        @RabbitHandler
        public String processMessage1(String message) {
            System.out.println(message);
            return "Test3 receiver1:"+message;
        }

        @RabbitHandler
        public String processMessage2(String[] message) {
            System.out.println(message.toString());
            return "Test3 receiver2:"+message.toString();
        }

}

