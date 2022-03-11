package ru.otus.pk.spring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.pk.spring.repository.BookRepository;


@DisplayName("Контроллер для работы с авторами должен ")
@WebFluxTest
@ContextConfiguration(classes = {AuthorController.class})
class AuthorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository repository;

    @DisplayName("возвращать список авторов")
    @Test
    public void findAllTest() {
        webTestClient.get()
                .uri("/authors")
                .exchange()
                .expectStatus()
                .isOk();
    }

}