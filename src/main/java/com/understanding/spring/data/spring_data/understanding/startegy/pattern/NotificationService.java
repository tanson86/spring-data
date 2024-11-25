package com.understanding.spring.data.spring_data.understanding.startegy.pattern;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final Map<NotificationType,NotificationStrategy> strategies;

    public NotificationService(List<NotificationStrategy> strategyList){
        strategies = strategyList.stream().map(Objects::requireNonNull).collect(Collectors.toMap(NotificationStrategy::getType, Function.identity()));
    }

    public void send(String message, NotificationType notificationType){
        NotificationStrategy strategy = strategies.get(notificationType);
        Objects.requireNonNull(strategy, "strategy is null");
        strategy.sendNotification(message);
    }
}
