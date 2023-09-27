package com.example.learndemo.mq.controller;

import com.example.learndemo.mq.service.ConfirmCallbackService;
import com.example.learndemo.mq.service.ReturnCallbackService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 * @Author: shiboyuan
 * @Date: 2021/3/19 14:04
 */
@Controller
@RequestMapping("/cs/testMq")
public class TestSendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ConfirmCallbackService confirmCallbackService;

    @Autowired
    private ReturnCallbackService returnCallbackService;


    /**
     * convertSendAndReceive(…)：可以同步消费者。使用此方法，当确认了所有的消费者都接收成功之后，
     * 才触发另一个convertSendAndReceive(…)，也就是才会接收下一条消息。RPC调用方式。
     *
     * convertAndSend(…)：使用此方法，交换机会马上把所有的信息都交给所有的消费者，
     * 消费者再自行处理，不会因为消费者处理慢而阻塞线程。
     */

    @RequestMapping("/sendqn")
    @ResponseBody
    public String sendqn() throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Double x = 0.0 ;
        Double y = 0.0 ;
        Double z = 0.0 ;

        for (int i =0;i < 100;i++){
            x = x + 0.5;
            String date = sdf.format(new Date());
            //String content="中钢邢机,加工二车间,display,LCIDh5Zko9~9,338f1," + date + "," + x + ",8.97,1.16";

            //LCIDh5Zko9~9,display,78,11,中钢邢机,加工二车间,1,338f1,14.43,8.98,1.16,75,2023-03-02 14:11:32

            String content="LCIDh5Zko9~9,display,78,11,中钢邢机,加工二车间,1,338f1," + x + ",8.98,1.16,75," + date;
            amqpTemplate.convertAndSend("qn_crane_pos_biz", content);

            System.out.println("发送MQ-qn_crane_pos_biz:" + i );
            Thread.sleep(1000l);
        }
        return "ok!";
    }

    @RequestMapping("/send1")
    @ResponseBody
    public String send1(){
        String content="Date:"+new Date();
        Object testMq_mes1 = amqpTemplate.convertSendAndReceive("testMq_mes1", content);
        return testMq_mes1.toString();
    }

    @RequestMapping("/send2")
    @ResponseBody
    public String send2(){
        String content="Date:"+new Date();
        Object testMq_mes2 =  amqpTemplate.convertSendAndReceive("testMq_mes2",content);
        return testMq_mes2.toString();
    }

    @RequestMapping("/send3")
    @ResponseBody
    public String send3(){
        String content="Date:"+new Date();
        Object testMq_mes3 = amqpTemplate.convertSendAndReceive("testMq_mes3", content);
        return testMq_mes3.toString();
    }

    @RequestMapping("/send4")
    @ResponseBody
    public String send4(){
        String[] content =new String[]{"1","2"};
        Object testMq_mes4 = amqpTemplate.convertSendAndReceive("testMq_mes3", content);
        return testMq_mes4.toString();
    }

    @RequestMapping("/send5")
    @ResponseBody
    public String send5(){
        String content ="111";
        amqpTemplate.convertAndSend("fanoutExchange", null, content);
        return "ok!";
    }

    @RequestMapping("/send6")
    @ResponseBody
    public String send6(){
        for (int i =0 ; i < 10 ; i++){
            amqpTemplate.convertAndSend("testMq_mes6", i);
        }
        return "ok!";
    }


    @RequestMapping("/send7")
    @ResponseBody
    public String send7(){
        String content ="111";
        amqpTemplate.convertAndSend("directExchange","direct_1",content);
        return "ok!";
    }

    @RequestMapping("/send8")
    @ResponseBody
    public String send8(){
        String content ="222";
        amqpTemplate.convertAndSend("directExchange","direct_2",content);
        return "ok!";
    }

    @RequestMapping("/send9")
    @ResponseBody
    public String send9(){
        String content ="1";
        amqpTemplate.convertAndSend("topicExchange","topic.insert",content);
        return "ok!";
    }
    @RequestMapping("/send10")
    @ResponseBody
    public String send10(){
        String content ="2";
        amqpTemplate.convertAndSend("topicExchange","topic.insert.update",content);
        return "ok!";
    }

    @RequestMapping("/send11")
    @ResponseBody
    public String send11(){
        String content ="3";
        amqpTemplate.convertAndSend("topicExchange","update.date",content);
        return "ok!";
    }


    @RequestMapping("/send12")
    @ResponseBody
    public String send12() {

        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        rabbitTemplate.setMandatory(true);

        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
        rabbitTemplate.setReturnCallback(returnCallbackService);


        //rabbitTemplate.convertAndSend(exchange, routingKey, msg, message);

        /**
         * 发送消息
         */
        /*rabbitTemplate.convertAndSend("fanoutExchange_confirm","", "cscs",
                                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));*/

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(UUID.randomUUID().toString());

        Message message = new Message("cscs".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("fanoutExchange_confirm","",message,
                new CorrelationData(UUID.randomUUID().toString()));
        return "ok!";
    }

}
