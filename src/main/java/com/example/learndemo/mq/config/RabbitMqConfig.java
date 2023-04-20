package com.example.learndemo.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: shiboyuan
 * @Description:队列配置，队列的名称，发送者和接受者的名称必须一致，否则接收不到消息
 * @Date: 2021/3/19 14:01
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue Queue1() {
        return new Queue("testMq_mes1");
    }

    @Bean
    public Queue Queue2() {
        return new Queue("testMq_mes2");
    }

    @Bean
    public Queue Queue3() {
        return new Queue("testMq_mes3");
    }

    @Bean
    public Queue testMq_mes_fanout_1() {
        return new Queue("testMq_mes_fanout_1");
    }

    @Bean
    public Queue testMq_mes_fanout_2() {
        return new Queue("testMq_mes_fanout_2");
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchange_fanout_1() {
        return BindingBuilder.bind(testMq_mes_fanout_1()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchange_fanout_2() {
        return BindingBuilder.bind(testMq_mes_fanout_2()).to(fanoutExchange());
    }


    @Bean
    public Queue testMq_mes_direct_1() {
        return new Queue("testMq_mes_direct_1");
    }

    @Bean
    public Queue testMq_mes_direct_2() {
        return new Queue("testMq_mes_direct_2");
    }

    @Bean
    public Queue testMq_mes_direct_3() {
        return new Queue("testMq_mes_direct_3");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding bindingExchange_direct_1() {
        return BindingBuilder.bind(testMq_mes_direct_1()).to(directExchange()).with("direct_1");
    }
    @Bean
    Binding bindingExchange_direct_2() {
        return BindingBuilder.bind(testMq_mes_direct_2()).to(directExchange()).with("direct_1");
    }
    @Bean
    Binding bindingExchange_direct_3() {
        return BindingBuilder.bind(testMq_mes_direct_3()).to(directExchange()).with("direct_2");
    }


    @Bean
    public Queue testMq_mes_confirm_1() {
        return new Queue("testMq_mes_confirm_1");
    }

    @Bean
    public Queue testMq_mes_confirm_2() {
        return new Queue("testMq_mes_confirm_2");
    }

    @Bean
    FanoutExchange fanoutExchange_confirm() {
        return new FanoutExchange("fanoutExchange_confirm");
    }

    @Bean
    Binding bindingExchange_confirm_1() {
        return BindingBuilder.bind(testMq_mes_confirm_1()).to(fanoutExchange_confirm());
    }

    @Bean
    Binding bindingExchange_confirm_2() {
        return BindingBuilder.bind(testMq_mes_confirm_2()).to(fanoutExchange_confirm());
    }

}
