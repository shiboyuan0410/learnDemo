package com.example.learndemo.thread.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 15:11
 */
@Service
@Slf4j
public class AysncTaskService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Async注解中指定线程池名,异步调用线程池去执行任务
     */
    @Async("asynTaskExecutor")
    public void excuteAysncTask(int i) throws InterruptedException{
        log.info("asynTaskExecutor1执行异步线程:"+i);
        Thread.sleep(500L);
    }


    /**
     * @Async注解中指定线程池名,异步调用线程池去执行任务
     */
    @Async("asynTaskExecutor")
    public CompletableFuture excuteAysncTask2(int i) throws InterruptedException{
        log.info("asynTaskExecutor2执行异步线程:"+i);
        Thread.sleep(500L);

        return CompletableFuture.completedFuture(i);
    }


    static int ticks_num = 40;
    public static void setTicks_num(int ticks_num) {
        AysncTaskService.ticks_num = ticks_num;
    }

    private Lock lock = new ReentrantLock();
    /**
     * 异步加锁实现 数据安全
     */
    @Async("asyncExecutor_ticks")
    public void sell_ticks(){

        lock.lock();
        try {
            if (ticks_num > 0) {
                ticks_num--;
                log.info("剩余票数:"+ticks_num);

                rabbitTemplate.convertAndSend("directExchange","direct_1",ticks_num);

                Thread.sleep(0);

            }else{
                log.info("已售完!");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
