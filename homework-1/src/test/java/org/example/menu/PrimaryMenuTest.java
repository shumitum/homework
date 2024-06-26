package org.example.menu;

import org.example.user.service.AuthenticationService;
import org.example.user.service.RegistrationService;
import org.example.user.service.impl.AuthenticationServiceImpl;
import org.example.user.service.impl.RegistrationServiceImpl;
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
class PrimaryMenuTest {

    private final PrimaryMenu primaryMenu = new PrimaryMenu();

    @Mock
    private RegistrationService registrationService = new RegistrationServiceImpl();

    @Mock
    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @Mock
    private Menu conferenceHallMenu = new ConferenceHallMenu();

    @Mock
    private Menu workplaceMenu = new WorkplaceMenu();

    @Mock
    private Menu bookingMenu = new BookingMenu();

    @BeforeEach
    void setUp() {
        primaryMenu.setRegistrationService(registrationService);
        primaryMenu.setAuthenticationService(authenticationService);
        primaryMenu.setConferenceHallMenu(conferenceHallMenu);
        primaryMenu.setWorkplaceMenu(workplaceMenu);
        primaryMenu.setBookingMenu(bookingMenu);
    }

    @Test
    void handleUserAction_whenInvokeFirstMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(registrationService).registerUser();

        System.setIn(new ByteArrayInputStream("1".getBytes()));

        assertThatThrownBy(primaryMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(registrationService, times(1)).registerUser();
    }

    @Test
    void handleUserAction_whenInvokeSecondMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(authenticationService).authenticateUser();

        System.setIn(new ByteArrayInputStream("2".getBytes()));

        assertThatThrownBy(primaryMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(authenticationService, times(1)).authenticateUser();
    }

    @Test
    void handleUserAction_whenInvokeThirdMenuOption_thenThrowsException() {
        when(authenticationService.checkAuthorizedUserExistence()).thenReturn(true);
        doThrow(NoSuchElementException.class).when(conferenceHallMenu).handleUserAction();

        System.setIn(new ByteArrayInputStream("3".getBytes()));

        assertThatThrownBy(primaryMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(authenticationService, times(2)).checkAuthorizedUserExistence();
        verify(conferenceHallMenu, times(1)).handleUserAction();
    }

    @Test
    void handleUserAction_whenInvokeFourthMenuOption_thenThrowsException() {
        when(authenticationService.checkAuthorizedUserExistence()).thenReturn(true);
        doThrow(NoSuchElementException.class).when(workplaceMenu).handleUserAction();

        System.setIn(new ByteArrayInputStream("4".getBytes()));

        assertThatThrownBy(primaryMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(authenticationService, times(2)).checkAuthorizedUserExistence();
        verify(workplaceMenu, times(1)).handleUserAction();
    }

    @Test
    void handleUserAction_whenInvokeFifthMenuOption_thenThrowsException() {
        when(authenticationService.checkAuthorizedUserExistence()).thenReturn(true);
        doThrow(NoSuchElementException.class).when(bookingMenu).handleUserAction();

        System.setIn(new ByteArrayInputStream("5".getBytes()));

        assertThatThrownBy(primaryMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(authenticationService, times(2)).checkAuthorizedUserExistence();
        verify(bookingMenu, times(1)).handleUserAction();
    }

    @Test
    void handleUserAction_whenInvokeSixthMenuOption_thenFinishProcess() {
        when(authenticationService.checkAuthorizedUserExistence()).thenReturn(true);

        System.setIn(new ByteArrayInputStream("6".getBytes()));

        primaryMenu.handleUserAction();
        verify(authenticationService, times(2)).checkAuthorizedUserExistence();
    }
}