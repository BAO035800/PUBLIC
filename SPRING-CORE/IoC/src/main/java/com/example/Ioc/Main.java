package com.example.Ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.Ioc"})
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        System.out.println("Hello World");
        // sms smsService = context.getBean(sms.class);
        // smsService.sendMessage();
        // email emailService = context.getBean(email.class);
        // emailService.sendMessage();
        UserNotification userNotification = context.getBean(UserNotification.class);
        userNotification.notifyUser();
    }
}
