package com.example.learndemo.assertLearn.exception;

import com.example.demo.lx.learn.assertLearn.model.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * 可以指定 Controller 范围
 *
 * basePackages： 指定一个或多个包，这些包及其子包下的所有 Controller 都被该 @ControllerAdvice 管理
 * basePackages={"top.onething"}
 *
 * basePackageClasses： 是 basePackages 的一种变形，指定一个或多个 Controller 类，这些类所属的包及其子包下的所有 Controller 都被该 @ControllerAdvice 管理
 * basePackageClasses={TestController.class}
 *
 * assignableTypes： 指定一个或多个 Controller 类，这些类被该 @ControllerAdvice 管理
 * assignableTypes={TestController.class}
 *
 * annotations： 指定一个或多个注解，被这些注解所标记的 Controller 会被该 @ControllerAdvice 管理
 * annotations = {TestAnnotation.class}
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandle {


    /**
     * 自定义异常处理
     */
    @ExceptionHandler(value = MyException.class)
    public void bizExceptionHandle(MyException myException){
        int code = myException.getCode();
        String msg = myException.getMsg();
        System.out.println("msg:" + msg + " code:" +code);
    }

    /**
     * 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandle(){

    }


}
