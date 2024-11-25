package com.understanding.spring.data.spring_data.understanding.startegy.pattern;

public interface NotificationStrategy {
    void sendNotification(String message);
    NotificationType getType();
}
