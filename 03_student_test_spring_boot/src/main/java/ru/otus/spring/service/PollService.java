package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.interfaces.IOService;

@RequiredArgsConstructor
@Service
public class PollService {

    private final IOService ioService;

    public void poll() {
        ioService.out("Началось!");

        ioService.out("Как вас зовут?");
        String name = ioService.readString();
        ioService.out(String.format("Приятно познакомиться: %s", name));
        ioService.out("Как ваши дела? Если хорошо, введите \"OK\"");
        String res = ioService.readString();
        if (res.equalsIgnoreCase("OK")) {
            ioService.out("Это радует");
        } else {
            ioService.out("Ничего, скоро все наладится!");
        }
        ioService.out(String.format("До свидания %s", name));
    }
}
