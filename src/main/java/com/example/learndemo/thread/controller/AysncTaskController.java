package com.example.learndemo.thread.controller;

import com.example.demo.lx.learn.thread.service.AysncTaskService;
import com.example.demo.lx.learn.thread.service.TicketOffice;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 15:15
 */
@Controller
@RequestMapping("/cs/task")
public class AysncTaskController {

    @Autowired
    private AysncTaskService aysncTaskService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/task1")
    @ResponseBody
    public String task1() {

        try {
            for (int i=0 ; i < 100 ; i++){
                aysncTaskService.excuteAysncTask(i);
                CompletableFuture completableFuture = aysncTaskService.excuteAysncTask2(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok!";
    }


    @RequestMapping("/task2")
    @ResponseBody
    public String task2() {

        AysncTaskService.setTicks_num(10);

        for (int i=0 ; i < 100 ; i++){
            aysncTaskService.sell_ticks();
        }

        return "ok!";
    }

    @RequestMapping("/task3")
    @ResponseBody
    public String task3() {
        TicketOffice to = new TicketOffice();

        TicketOffice.setTicks_num(20);
        TicketOffice.setRabbitTemplate(rabbitTemplate);

        Thread ticketOffice_1 = new Thread(to,"窗口1:");
        Thread ticketOffice_2 = new Thread(to,"窗口2:");
        Thread ticketOffice_3 = new Thread(to,"窗口3:");

        ticketOffice_1.start();
        ticketOffice_2.start();
        ticketOffice_3.start();


        File f = new File("");

        return "ok!";
    }
}
