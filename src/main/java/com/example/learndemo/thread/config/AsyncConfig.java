package com.example.learndemo.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 15:09
 */
@Configuration
@EnableAsync  // 启用异步任务
public class AsyncConfig {

    @Bean("asynTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程数：线程池创建时候初始化的线程数
        executor.setCorePoolSize(5);

        //最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(500);

        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);

        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("CS-Async-");
        executor.initialize();
        return executor;
    }


    @Bean("asyncExecutor_ticks")
    public Executor asyncExecutor_ticks() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程数：线程池创建时候初始化的线程数
        executor.setCorePoolSize(5);

        //最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(500);

        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);

        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("Tick-Async-");
        executor.initialize();
        return executor;
    }

}
