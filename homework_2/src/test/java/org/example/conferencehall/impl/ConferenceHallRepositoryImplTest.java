package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.example.testcontainer.TestContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConferenceHallRepositoryImplTest {

    private ConferenceHallRepositoryImpl conferenceHallRepository = new ConferenceHallRepositoryImpl();
    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;
    private ConferenceHall conferenceHall;

    @BeforeAll
    static void setUpAll() {
        postgreSQLContainer = TestContainer.getPostgresContainer();

    }

    @BeforeEach
    void setUp() {
        conferenceHall = new ConferenceHall(1, "cool name");
    }

    @Test
    @DisplayName("Поиск всех конференц-залов")
    void findAll() {
        List<ConferenceHall> all = conferenceHallRepository.findAll();

        assertThat(all)
                .contains(conferenceHall)
                .hasSize(1);
    }

    @Test
    @DisplayName("Проверка существования конференц-зала по ID")
    void existsById_whenInvokeWithValidConferenceHallId_whenReturnTrue() {
        boolean isConferenceHallExists = conferenceHallRepository.existsById(1);

        assertThat(isConferenceHallExists)
                .isTrue();
    }

    @Test
    @DisplayName("Проверка существования конференц-зала по несуществующему ID")
    void existsById_whenInvokeWithInValidConferenceHallId_whenReturnFalse() {
        boolean isConferenceHallExists = conferenceHallRepository.existsById(3);

        assertThat(isConferenceHallExists)
                .isFalse();
    }
}