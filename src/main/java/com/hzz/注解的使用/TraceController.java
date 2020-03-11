package com.hzz.注解的使用;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceController {

    @Autowired
    TraceServiceimp traceServiceimp;

    @RequestMapping("/login")
    @TraceLog(business = "CRM", module = "LOGIN")
    public String login(String userName) {
        traceServiceimp.test(new LoginParam("1","2"));
        return userName;
    }
}
