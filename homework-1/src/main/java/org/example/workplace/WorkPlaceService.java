package org.example.workplace;

import org.example.workplace.model.Workplace;

import java.util.List;

public interface WorkPlaceService {
    void createWorkplace();

    void updateWorkplace();

    void deleteWorkplace();

    List<Workplace> findAllWorkplaces();
}
