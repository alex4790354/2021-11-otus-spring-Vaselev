package ru.otus.spring.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EventHalfGlass extends ApplicationEvent {

    @Getter
    private final String payload;

    public EventHalfGlass(Object source) {
        super(source);
        payload = "Осталось половина стакана воды!!!";
    }
}
