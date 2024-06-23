package org.example.workplace.impl;

import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;
import org.example.workplace.WorkPlaceService;
import org.example.workplace.model.Workplace;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WorkPlaceServiceImpl implements WorkPlaceService {

    private CrudRepository<Workplace> workPlaceRepository = ApplicationContext
            .getInstance().getWorkPlaceRepository();
    private int id = 0;

    public void setWorkPlaceRepository(CrudRepository<Workplace> workPlaceRepository) {
        this.workPlaceRepository = workPlaceRepository;
    }

    @Override
    public void createWorkplace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите этаж рабочего места:");
        try {
            Integer floor = scanner.nextInt();
            Workplace workplace = new Workplace(++id, floor);
            workPlaceRepository.save(workplace);
        } catch (InputMismatchException e) {
            System.out.println("Этаж должен быть цифрой");
        }
    }

    @Override
    public void updateWorkplace() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите ID редактируемого раб. места:");
            Integer placeId = scanner.nextInt();
            try {
                System.out.println("Введите новый этаж рабочего места:");
                Integer floor = scanner.nextInt();
                Workplace workplace = new Workplace(placeId, floor);
                workPlaceRepository.update(workplace);
            } catch (InputMismatchException e) {
                System.out.println("Этаж должен быть цифрой");
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputMismatchException e) {
            System.out.println("ID рабочего места должен быть цифрой");
        }
    }

    @Override
    public void deleteWorkplace() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите ID удаляемого рабочего места:");
            Integer workplaceId = scanner.nextInt();
            workPlaceRepository.delete(workplaceId);
        } catch (InputMismatchException e) {
            System.out.println("ID рабочего места должен быть цифрой");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Workplace> findAllWorkplaces() {
        return workPlaceRepository.findAll();
    }
}
