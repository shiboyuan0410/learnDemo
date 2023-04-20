package com.example.learndemo.thread.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 15:11
 */
@Slf4j
public class TicketOffice implements Runnable{

    static int ticks_num = 40;

    public static void setTicks_num(int ticks_num) {
        TicketOffice.ticks_num = ticks_num;
    }

    static RabbitTemplate rabbitTemplate;

    public static void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        TicketOffice.rabbitTemplate = rabbitTemplate;
    }

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (ticks_num > 0){
            lock.lock();
            try {
                if (ticks_num > 0) {
                    ticks_num--;

                    log.info(Thread.currentThread().getName()+"卖出一张,剩余票数:"+ticks_num);

                    rabbitTemplate.convertAndSend("directExchange","direct_1",ticks_num);

                    Thread.sleep(500L);

                }else{
                    log.info("已售完!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
