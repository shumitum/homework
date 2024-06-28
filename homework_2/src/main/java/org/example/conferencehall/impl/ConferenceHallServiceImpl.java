package org.example.conferencehall.impl;

import lombok.Setter;
import org.example.conferencehall.ConferenceHallService;
import org.example.conferencehall.model.ConferenceHall;
import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;
import org.example.in.UserInput;
import org.example.out.Output;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

@Setter
public class ConferenceHallServiceImpl implements ConferenceHallService {

    private CrudRepository<ConferenceHall> conferenceHallRepository;

    public ConferenceHallServiceImpl() {
        this.conferenceHallRepository = ApplicationContext.getInstance().getConferenceHallRepository();
    }

    @Override
    public void createConferenceHall() {
        String name = UserInput.stringInput("Введите имя конференц-зала:");
        conferenceHallRepository.save(new ConferenceHall(null, name));
    }

    @Override
    public void updateConferenceHall() {
        try {
            Integer placeId = UserInput.digitInput("Введите ID редактируемого конф.-зала:");
            String name = UserInput.stringInput("Введите новое имя конф.-зала:");
            conferenceHallRepository.update(new ConferenceHall(placeId, name));
        } catch (InputMismatchException e) {
            Output.printMessage("ID конф.-зала должен быть цифрой");
        } catch (NoSuchElementException e) {
            Output.printMessage(e.getMessage());
        }
    }

    @Override
    public void deleteConferenceHall() {
        try {
            Integer conferenceHallId = UserInput.digitInput("Введите ID удаляемого конф.-зала:");
            conferenceHallRepository.delete(conferenceHallId);
        } catch (InputMismatchException e) {
            Output.printMessage("ID конф.-зала должен быть цифрой");
        } catch (NoSuchElementException e) {
            Output.printMessage(e.getMessage());
        }
    }

    @Override
    public List<ConferenceHall> findAllConferenceHalls() {
        Output.printMessage("Список всех конференц-залов: ");
        return conferenceHallRepository.findAll();
    }
}
