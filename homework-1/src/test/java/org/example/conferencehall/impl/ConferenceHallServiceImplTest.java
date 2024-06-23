package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.example.crud.CrudRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConferenceHallServiceImplTest {

    private final ConferenceHallServiceImpl conferenceHallService = new ConferenceHallServiceImpl();

    @Mock
    private CrudRepository<ConferenceHall> conferenceHallRepository = new ConferenceHallRepositoryImpl();

    @BeforeEach
    void setUp() {
        conferenceHallService.setConferenceHallRepository(conferenceHallRepository);
    }

    @Test
    void createConferenceHall() {
    }

    @Test
    void updateConferenceHall() {
    }

    @Test
    void deleteConferenceHall() {
    }

    @Test
    void findAllConferenceHalls() {
    }
}