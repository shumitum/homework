package org.example.menu;

import org.example.workplace.WorkPlaceService;
import org.example.workplace.impl.WorkPlaceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkplaceMenuTest {

    private final WorkplaceMenu workplaceMenu = new WorkplaceMenu();

    @Mock
    private final WorkPlaceService workPlaceService = new WorkPlaceServiceImpl();

    @BeforeEach
    void setUp() {
        workplaceMenu.setWorkPlaceService(workPlaceService);
    }

    @Test
    void handleUserAction_whenInvokeFirstMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workPlaceService).createWorkplace();

        System.setIn(new ByteArrayInputStream("1".getBytes()));

        assertThatThrownBy(workplaceMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workPlaceService, times(1)).createWorkplace();
    }

    @Test
    void handleUserAction_whenInvokeSecondMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workPlaceService).updateWorkplace();

        System.setIn(new ByteArrayInputStream("2".getBytes()));

        assertThatThrownBy(workplaceMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workPlaceService, times(1)).updateWorkplace();
    }

    @Test
    void handleUserAction_whenInvokeThirdMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workPlaceService).deleteWorkplace();

        System.setIn(new ByteArrayInputStream("3".getBytes()));

        assertThatThrownBy(workplaceMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workPlaceService, times(1)).deleteWorkplace();
    }

    @Test
    void handleUserAction_whenInvokeFourthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workPlaceService).findAllWorkplaces();

        System.setIn(new ByteArrayInputStream("4".getBytes()));

        assertThatThrownBy(workplaceMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workPlaceService, times(1)).findAllWorkplaces();
    }
}