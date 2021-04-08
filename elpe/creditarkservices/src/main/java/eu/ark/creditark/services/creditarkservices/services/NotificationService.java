package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.notification.Notification;

public interface NotificationService {
    public void notify(Notification notification, String username);
}
