package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public void notify(Notification notification, String username) {

        messagingTemplate.convertAndSendToUser(
                username,
                "/queue/notify",
                notification
        );
        return;
    }

    public SimpMessagingTemplate getMessagingTemplate() {
        return messagingTemplate;
    }

    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
}
