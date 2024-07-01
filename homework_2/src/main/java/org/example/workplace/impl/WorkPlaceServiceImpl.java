package org.example.workplace.impl;

import lombok.Setter;
import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;
import org.example.in.UserInput;
import org.example.out.Output;
import org.example.workplace.WorkPlaceService;
import org.example.workplace.model.Workplace;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

@Setter
public class WorkPlaceServiceImpl implements WorkPlaceService {

    private CrudRepository<Workplace> workPlaceRepository;

    public WorkPlaceServiceImpl() {
        this.workPlaceRepository = ApplicationContext.getInstance().getWorkPlaceRepository();
    }

    @Override
    public void createWorkplace() {
        try {
            Integer floor = UserInput.digitInput("Введите этаж рабочего места:");
            workPlaceRepository.save(new Workplace(null, floor));
        } catch (InputMismatchException e) {
            Output.printMessage("Этаж должен быть цифрой");
        }
    }

    @Override
    public void updateWorkplace() {
        try {
            Integer placeId = UserInput.digitInput("Введите ID редактируемого раб. места:");
            try {
                Integer floor = UserInput.digitInput("Введите новый этаж рабочего места:");
                Workplace workplace = new Workplace(placeId, floor);
                workPlaceRepository.update(workplace);
            } catch (InputMismatchException e) {
                Output.printMessage("Этаж должен быть цифрой");
            } catch (NoSuchElementException e) {
                Output.printMessage(e.getMessage());
            }
        } catch (InputMismatchException e) {
            Output.printMessage("ID рабочего места должен быть цифрой");
        }
    }

    @Override
    public void deleteWorkplace() {
        try {
            Integer workplaceId = UserInput.digitInput("Введите ID удаляемого рабочего места:");
            workPlaceRepository.delete(workplaceId);
        } catch (InputMismatchException e) {
            Output.printMessage("ID рабочего места должен быть цифрой");
        } catch (NoSuchElementException e) {
            Output.printMessage(e.getMessage());
        }
    }

    @Override
    public List<Workplace> findAllWorkplaces() {
        Output.printMessage("Список всех рабочих мест: ");
        return workPlaceRepository.findAll();
    }
}
