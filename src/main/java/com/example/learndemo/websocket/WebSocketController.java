package com.example.learndemo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shiboyuan
 * @Date: 2021/4/9 10:57
 */
@Controller
public class WebSocketController {

    @Autowired
    private WsServerEndpoint wsServerEndpoint;

    @GetMapping("/webSocket")
    public String socket() {
        return "websocket/webSocket";
    }


}
