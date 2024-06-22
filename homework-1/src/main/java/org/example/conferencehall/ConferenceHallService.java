package org.example.conferencehall;

import org.example.conferencehall.model.ConferenceHall;

import java.util.List;

public interface ConferenceHallService {
    void createConferenceHall();

    void updateConferenceHall();

    void deleteConferenceHall();

    List<ConferenceHall> findAllConferenceHalls();
}