package com.example.learndemo.assertLearn.controller;

import com.example.learndemo.assertLearn.model.MyAssert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 断言的学习
 * 1.统一异常处理 枚举+断言 替代try catch
 * 2.优雅的格式
 */
@Controller
@RequestMapping("/assert")
public class AssertController {

    public static void main(String[] args) {
        cs("");
    }

    @RequestMapping("/cs1")
    public static void cs(@PathVariable(name = "test") String test){
        MyAssert.isNull(test,"不能为空!");
    }


}
