package org.example.conferencehall.impl;

import org.example.conferencehall.ConferenceHallService;
import org.example.conferencehall.model.ConferenceHall;
import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConferenceHallServiceImpl implements ConferenceHallService {

    private final CrudRepository<ConferenceHall> conferenceHallRepository = ApplicationContext
            .getInstance().getConferenceHallRepository();
    private int id = 0;

    @Override
    public void createConferenceHall() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя конференц-зала:");
        String name = scanner.next();
        ConferenceHall conferenceHall = new ConferenceHall(++id, name);
        conferenceHallRepository.save(conferenceHall);
    }


    @Override
    public void updateConferenceHall() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID редактируемого конф.-зала:");
        try {
            Integer placeId = scanner.nextInt();
            System.out.println("Введите новое имя конф.-зала:");
            String name = scanner.next();
            ConferenceHall conferenceHall = new ConferenceHall(placeId, name);
            conferenceHallRepository.update(conferenceHall);
        } catch (InputMismatchException e) {
            System.out.println("ID конф.-зала должен быть цифрой");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteConferenceHall() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите ID удаляемого конф.-зала:");
            Integer conferenceHallId = scanner.nextInt();
            conferenceHallRepository.delete(conferenceHallId);
        } catch (InputMismatchException e) {
            System.out.println("ID конф.-зала должен быть цифрой");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ConferenceHall> findAllConferenceHalls() {
        return conferenceHallRepository.findAll();
    }
}
