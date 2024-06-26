package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        conferenceHall = new ConferenceHall(1, "name");
    }

    @AfterEach
    void tearDown() {
        conferenceHallRepository = null;
    }

    @Test
    void save_whenInvokeWithValidConferenceHall_whenSaveConferenceHall() {
        conferenceHallRepository.save(conferenceHall);

        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();

        assertThat(halls)
                .containsEntry(1, conferenceHall);
    }

    @Test
    void save_whenInvokeWithNullConferenceHall_whenDonTSaveConferenceHall() {
        conferenceHallRepository.save(null);

        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();

        assertThat(halls)
                .isEmpty();
    }

    @Test
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
    void update_whenInvokeWithNonExistsConferenceHall_whenDontUpdateConferenceHall() {
        conferenceHallRepository.save(conferenceHall);
        ConferenceHall newConferenceHall = new ConferenceHall(2, "anotherName");

        assertThatThrownBy(() -> conferenceHallRepository.update(newConferenceHall)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete_whenInvokeWithValidConferenceHall_whenDeleteConferenceHall() {
        conferenceHallRepository.save(conferenceHall);
        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();
        assertThat(halls).containsEntry(1, conferenceHall);

        conferenceHallRepository.delete(1);

        assertThat(halls)
                .doesNotContainEntry(1, conferenceHall);
    }

    @Test
    void delete_whenInvokeWithNonExistsConferenceHall_whenDontDeleteConferenceHall() {
        Map<Integer, ConferenceHall> halls = conferenceHallRepository.getHalls();
        assertThat(halls).isEmpty();
        assertThatThrownBy(() -> conferenceHallRepository.delete(2)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findAll() {
        ConferenceHall newConferenceHall = new ConferenceHall(2, "anotherName");
        conferenceHallRepository.save(conferenceHall);
        conferenceHallRepository.save(newConferenceHall);

        List<ConferenceHall> all = conferenceHallRepository.findAll();

        assertThat(all)
                .contains(newConferenceHall)
                .contains(conferenceHall)
                .hasSize(2);
    }

    @Test
    void existsById_whenInvokeWithValidConferenceHallId_whenReturnTrue() {
        conferenceHallRepository.save(conferenceHall);

        boolean isConferenceHallExists = conferenceHallRepository.existsById(1);

        assertThat(isConferenceHallExists)
                .isTrue();
    }

    @Test
    void existsById_whenInvokeWithInValidConferenceHallId_whenReturnFalse() {
        conferenceHallRepository.save(conferenceHall);

        boolean isConferenceHallExists = conferenceHallRepository.existsById(2);

        assertThat(isConferenceHallExists)
                .isFalse();
    }
}