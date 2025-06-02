package com.example.Ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Lazy
@Component
public class UserNotification {

    // private final List<MessageService> messageServices;

    // @Autowired
    // public UserNotification(List<MessageService> messageServices) {
    //     this.messageServices = messageServices;
    // }

    // public void sendNotification() {
    //     for (MessageService service : messageServices) {
    //         service.sendMessage();
    //     }
    // }
    @Autowired
    @Qualifier("sms")
    public MessageService messageService;
    public void notifyUser() {
        messageService.sendMessage();  
    }
}
