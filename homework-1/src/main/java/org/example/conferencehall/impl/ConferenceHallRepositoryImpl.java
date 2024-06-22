package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.example.crud.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ConferenceHallRepositoryImpl implements CrudRepository<ConferenceHall> {

    private final Map<Integer, ConferenceHall> halls = new HashMap<>();

    @Override
    public void save(ConferenceHall conferenceHall) {
        if (conferenceHall != null) {
            halls.put(conferenceHall.getConferenceHallId(), conferenceHall);
            System.out.printf("Конференц-зал создан - %s%n%n", conferenceHall);
        } else {
            System.out.println("ConferenceHall is null, place wasn't saved");
        }
    }

    @Override
    public void update(ConferenceHall conferenceHall) {
        if (halls.containsKey(conferenceHall.getConferenceHallId())) {
            halls.put(conferenceHall.getConferenceHallId(), conferenceHall);
            System.out.printf("Данные конференц-зала обновлены - %s%n%n", conferenceHall);
        } else {
            throw new NoSuchElementException("ConferenceHall not found, place wasn't updated");
        }
    }

    @Override
    public void delete(Integer id) {
        if (halls.containsKey(id)) {
            halls.remove(id);
            System.out.println("ConferenceHall was deleted");
        } else {
            throw new NoSuchElementException("ConferenceHall not found, place wasn't deleted");
        }
    }

    @Override
    public List<ConferenceHall> findAll() {
        return halls.values().stream()
                .toList();
    }

    @Override
    public boolean existsWorkplace(Integer id) {
        return halls.containsKey(id);
    }
}
