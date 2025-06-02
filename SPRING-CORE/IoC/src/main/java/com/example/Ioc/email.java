package com.example.Ioc;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary
public class email implements MessageService {
    public email () {
        System.out.println("email duoc khoi tao");
    }
    @Override
    public void sendMessage(){
        System.out.println("Tin nhắn từ email");
    }
    
}
