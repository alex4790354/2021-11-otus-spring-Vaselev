package ru.otus.spring.events;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.interfaces.QuestionsService;

@Component
public class PositiveRespondent  {

    QuestionsService questionsService;

    @Autowired
    public PositiveRespondent(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @SneakyThrows
    @EventListener
    public void onApplicationEvent(HalfAGlassOfWaterEvent halfAGlassOfWaterEvent) {
        questionsService.takeExam();
    }
}
