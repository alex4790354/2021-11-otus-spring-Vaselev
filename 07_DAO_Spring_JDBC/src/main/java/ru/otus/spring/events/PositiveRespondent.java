package ru.otus.spring.events;

import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class PositiveRespondent  {


    @SneakyThrows
    @EventListener
    public void onApplicationEvent(HalfAGlassOfWaterEvent halfAGlassOfWaterEvent) {
        System.out.println("Hello world from PositiveRespondent");
    }
}
