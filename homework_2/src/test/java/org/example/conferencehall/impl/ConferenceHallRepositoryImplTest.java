package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ConferenceHallRepositoryImplTest {

    private ConferenceHallRepositoryImpl conferenceHallRepository;

    private ConferenceHall conferenceHall;

    @BeforeEach
    void setUp() {
        conferenceHallRepository = new ConferenceHallRepositoryImpl();
        conferenceHall = new ConferenceHall(1, "cool name");
    }

    @AfterEach
    void tearDown() {
        conferenceHallRepository = null;
    }

    /*@Test
    @DisplayName("")
    void save_whenInvokeWithValidConferenceHall_whenSaveConferenceHall() {
        conferenceHallRepository.save(conferenceHall);

        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();

        assertThat(halls)
                .containsEntry(1, conferenceHall);
    }

    @Test
    @DisplayName("")
    void save_whenInvokeWithNullConferenceHall_whenDonTSaveConferenceHall() {
        conferenceHallRepository.save(null);

        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();

        assertThat(halls)
                .isEmpty();
    }

    @Test
    @DisplayName("")
    void update_whenInvokeWithValidConferenceHall_whenUpdateConferenceHall() {
        conferenceHallRepository.save(conferenceHall);
        ConferenceHall newConferenceHall = new ConferenceHall(1, "anotherName");

        conferenceHallRepository.update(newConferenceHall);

        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();

        assertThat(halls)
                .containsEntry(1, newConferenceHall)
                .doesNotContainEntry(1, conferenceHall);
    }

    @Test
    @DisplayName("")
    void update_whenInvokeWithNonExistsConferenceHall_whenDontUpdateConferenceHall() {
        conferenceHallRepository.save(conferenceHall);
        ConferenceHall newConferenceHall = new ConferenceHall(2, "anotherName");

        assertThatThrownBy(() -> conferenceHallRepository.update(newConferenceHall)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("")
    void delete_whenInvokeWithValidConferenceHall_whenDeleteConferenceHall() {
        conferenceHallRepository.save(conferenceHall);
        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();
        assertThat(halls).containsEntry(1, conferenceHall);

        conferenceHallRepository.delete(1);

        assertThat(halls)
                .doesNotContainEntry(1, conferenceHall);
    }

    @Test
    @DisplayName("")
    void delete_whenInvokeWithNonExistsConferenceHall_whenDontDeleteConferenceHall() {
        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();
        assertThat(halls).isEmpty();
        assertThatThrownBy(() -> conferenceHallRepository.delete(2)).isInstanceOf(NoSuchElementException.class);
    }*/

    @Test
    @DisplayName("")
    void findAll() {
        List<ConferenceHall> all = conferenceHallRepository.findAll();

        assertThat(all)
                .contains(conferenceHall)
                .hasSize(1);
    }

    @Test
    @DisplayName("")
    void existsById_whenInvokeWithValidConferenceHallId_whenReturnTrue() {
        conferenceHallRepository.save(conferenceHall);

        boolean isConferenceHallExists = conferenceHallRepository.existsById(1);

        assertThat(isConferenceHallExists)
                .isTrue();
    }

    @Test
    @DisplayName("")
    void existsById_whenInvokeWithInValidConferenceHallId_whenReturnFalse() {
           boolean isConferenceHallExists = conferenceHallRepository.existsById(3);

        assertThat(isConferenceHallExists)
                .isFalse();
    }
}