package com.understanding.spring.data.spring_data.understanding.startegy.pattern;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.println(String.format("%s via sms notification", message));
    }

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }
}
