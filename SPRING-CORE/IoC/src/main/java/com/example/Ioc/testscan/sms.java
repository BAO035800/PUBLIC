package com.example.Ioc.testscan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.Ioc.MessageService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Lazy
@Component("sms")
public class sms implements MessageService {

    @PostConstruct
    public void init() {
        System.out.println("sms been post construct");
    }
    @Override
    public void sendMessage(){
        System.out.println("Tin nhắn từ sms" );
    }
    @PreDestroy
    public void destroy() {
        System.out.println("sms been destroy");
    };
    public sms() {
        System.out.println("sms duoc khoi tao");
        System.out.println("Trong constructor, zaloService = " + zaloService);
    }
    @Autowired
    public zalo zaloService;
}
