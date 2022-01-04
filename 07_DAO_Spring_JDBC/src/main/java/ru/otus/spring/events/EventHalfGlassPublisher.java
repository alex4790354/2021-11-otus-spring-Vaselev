package ru.otus.spring.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventHalfGlassPublisher implements EventsPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish() {
        publisher.publishEvent(new EventHalfGlass(this));
    }
}
