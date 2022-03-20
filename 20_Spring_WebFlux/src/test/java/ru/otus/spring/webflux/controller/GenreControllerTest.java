package ru.otus.spring.webflux.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.spring.webflux.controller.GenreController;
import ru.otus.spring.webflux.repository.BookRepository;


@DisplayName("Контроллер для работы с жанрами должен ")
@WebFluxTest
@ContextConfiguration(classes = {GenreController.class})
class GenreControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository repository;

    @DisplayName("возвращать список жанров")
    @Test
    public void findAllTest() {
        webTestClient.get()
                .uri("/genres")
                .exchange()
                .expectStatus()
                .isOk();
    }

}