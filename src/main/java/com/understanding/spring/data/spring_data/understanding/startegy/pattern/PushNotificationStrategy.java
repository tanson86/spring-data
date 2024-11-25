package com.understanding.spring.data.spring_data.understanding.startegy.pattern;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println(String.format("%s via push notification", message));
    }

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }
}
