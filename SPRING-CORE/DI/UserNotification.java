public class UserNotification {
    private MessageService service;

    public UserNotification(MessageService service11) {
        this.service = service11;
    }

    public void notifyUser() {
        service.sendMessage();
    }
    public static void main(String[] args) {
        MessageService emailService = new email();
        UserNotification emailNotification = new UserNotification(emailService);
        emailNotification.notifyUser();
    
        MessageService smsService = new sms();
        UserNotification smsNotification = new UserNotification(smsService);
        smsNotification.notifyUser();
        //Nếu 0 dùng DI thì code sẽ như này nghĩa là viết thông thường thì DI sẽ chặt chẽ nhưng viết DI thì sẽ lỏng hơn
        // public class UserNotification {
        //     public static void main(String[] args){
        //         MessageService email = new email();
        //         email.sendMessage();
        //         MessageService sms = new sms();
        //         sms.sendMessage();
        //     }
        // } 
    }
    
}
