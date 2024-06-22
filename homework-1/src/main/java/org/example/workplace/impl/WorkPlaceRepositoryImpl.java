package org.example.workplace.impl;

import org.example.crud.CrudRepository;
import org.example.workplace.model.Workplace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class WorkPlaceRepositoryImpl implements CrudRepository<Workplace> {

    private final Map<Integer, Workplace> workplaces = new HashMap<>();

    @Override
    public void save(Workplace workplace) {
        if (workplace != null) {
            workplaces.put(workplace.getWorkplaceId(), workplace);
            System.out.printf("Рабочее место создано - %s%n%n", workplace);
        } else {
            System.out.println("Рабочее место is null, Рабочее место wasn't saved");
        }
    }

    @Override
    public void update(Workplace workplace) {
        if (workplaces.containsKey(workplace.getWorkplaceId())) {
            workplaces.put(workplace.getWorkplaceId(), workplace);
            System.out.printf("Данные рабочего места обновлены - %s%n%n", workplace);
        } else {
            throw new NoSuchElementException("Рабочее место not found, Рабочее место wasn't updated");
        }
    }

    @Override
    public void delete(Integer id) {
        if (workplaces.containsKey(id)) {
            workplaces.remove(id);
            System.out.println("Рабочее место was deleted");
        } else {
            throw new NoSuchElementException("Рабочее место not found, Рабочее место wasn't deleted");
        }
    }

    @Override
    public List<Workplace> findAll() {
        return workplaces.values().stream()
                .toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return workplaces.containsKey(id);
    }
}
