package org.example.menu;

import org.example.workplace.WorkPlaceService;
import org.example.workplace.impl.WorkPlaceServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WorkplaceMenuTest {

    private final WorkplaceMenu workplaceMenu = new WorkplaceMenu();

    @Mock
    private final WorkPlaceService workPlaceService = new WorkPlaceServiceImpl();

    @BeforeEach
    void setUp() {
        workplaceMenu.setWorkPlaceService(workPlaceService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleUserAction() {
    }
}