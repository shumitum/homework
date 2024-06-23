package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void existsById() {
    }
}