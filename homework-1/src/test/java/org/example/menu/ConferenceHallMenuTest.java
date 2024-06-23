package org.example.menu;

import org.example.conferencehall.ConferenceHallService;
import org.example.conferencehall.impl.ConferenceHallServiceImpl;
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
class ConferenceHallMenuTest {

    private final ConferenceHallMenu conferenceHallMenu = new ConferenceHallMenu();

    @Mock
    private final ConferenceHallService conferenceHallService = new ConferenceHallServiceImpl();

    @BeforeEach
    void setUp() {
        conferenceHallMenu.setConferenceHallService(conferenceHallService);
    }

    @Test
    void handleUserAction_whenInvokeFirstMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallService).createConferenceHall();

        System.setIn(new ByteArrayInputStream("1".getBytes()));

        assertThatThrownBy(conferenceHallMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallService, times(1)).createConferenceHall();
    }

    @Test
    void handleUserAction_whenInvokeSecondMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallService).updateConferenceHall();

        System.setIn(new ByteArrayInputStream("2".getBytes()));

        assertThatThrownBy(conferenceHallMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallService, times(1)).updateConferenceHall();
    }

    @Test
    void handleUserAction_whenInvokeThirdMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallService).deleteConferenceHall();

        System.setIn(new ByteArrayInputStream("3".getBytes()));

        assertThatThrownBy(conferenceHallMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallService, times(1)).deleteConferenceHall();
    }

    @Test
    void handleUserAction_whenInvokeFourthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallService).findAllConferenceHalls();

        System.setIn(new ByteArrayInputStream("4".getBytes()));

        assertThatThrownBy(conferenceHallMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallService, times(1)).findAllConferenceHalls();
    }
}