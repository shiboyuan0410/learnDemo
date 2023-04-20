package com.example.learndemo.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅模型-Direct 路由模式
 *
 * 队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）
 * 消息的发送方在 向 Exchange发送消息时，也必须指定消息的 RoutingKey。
 * Exchange不再把消息交给每一个绑定的队列，而是根据消息的Routing Key进行判断，只有队列的 Routingkey与消息的 Routing key完全一致，才会接收到消息
 * @Author: shiboyuan
 * @Date: 2021/3/19 14:12
 */

@Component
public class Receiver5 {

    @RabbitListener(queues = "testMq_mes_direct_1")
    public void receiver3(String msg){

        System.out.println("库存信息更新:"+msg);
    }

    @RabbitListener(queues = "testMq_mes_direct_2")
    public void receiver4(String msg){

        System.out.println("快递信息更新:"+msg);
    }

    @RabbitListener(queues = "testMq_mes_direct_3")
    public void receiver5(String msg){
        System.out.println("Test3 receiver5:"+msg);
    }
}

