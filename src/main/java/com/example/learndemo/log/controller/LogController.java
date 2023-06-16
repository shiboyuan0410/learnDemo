package com.example.learndemo.log.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/log")
public class LogController {

    /*
     * 自定义日志输出到文件
     * */
    private static Logger test_logger = LogManager.getLogger("testLogger");


    @RequestMapping("/cs1")
    public void cs(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = sdf.format(new Date());
        test_logger.info("cscscs:{}",format);
    }

}
