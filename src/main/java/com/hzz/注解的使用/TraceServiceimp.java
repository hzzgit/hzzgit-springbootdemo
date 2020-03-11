package com.hzz.注解的使用;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Log4j2
//无（No）  参数（Args） 构造（Constructor）
@NoArgsConstructor
//所有（All）  参数（Args） 构造（Constructor）
@AllArgsConstructor
@TraceLog
public class TraceServiceimp {
    private String name;

    @Setter
    @Getter
    private int sex;
    @PostConstruct
    private void init() {
        test(new LoginParam("1","12"));
        new Thread(()->{
            while (true){

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @TraceLog(business = "循环的", module ="尝试模块" )
    public LoginParam test(LoginParam loginParam){
        loginParam.setPassword("123");
        loginParam.setUserName("小米");
        return  loginParam;
    }


    public static void main(String[] args) {
        TraceServiceimp traceServiceimp = new TraceServiceimp("1", 1);
        traceServiceimp.setSex(33);
        System.out.println(traceServiceimp.getSex());
    }
}
