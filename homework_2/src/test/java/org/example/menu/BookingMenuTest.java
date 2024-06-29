package org.example.menu;

import org.example.booking.BookingService;
import org.example.booking.controller.ConferenceHallBookingController;
import org.example.booking.controller.WorkplaceBookingController;
import org.example.booking.impl.conferencehall.ConferenceHallBookingServiceImpl;
import org.example.booking.impl.workplace.WorkplaceBookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingMenuTest {

    private final BookingMenu bookingMenu = new BookingMenu();

    @Mock
    private final WorkplaceBookingController workplaceBookingController = new WorkplaceBookingController();

    @Mock
    private final ConferenceHallBookingController conferenceHallBookingController = new ConferenceHallBookingController();

    @BeforeEach
    void setUp() {
        bookingMenu.setWorkplaceBookingController(workplaceBookingController);
        bookingMenu.setConferenceHallBookingController(conferenceHallBookingController);
    }

    @Test
    @DisplayName("Some text")
    void handleUserAction_whenInvokeFirstMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingController).createBooking();

        System.setIn(new ByteArrayInputStream("1".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingController, times(1)).createBooking();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeSecondMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingController).createBooking();

        System.setIn(new ByteArrayInputStream("2".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingController, times(1)).createBooking();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeThirdMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingController).cancelBooking();

        System.setIn(new ByteArrayInputStream("3".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingController, times(1)).cancelBooking();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeFourthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingController).cancelBooking();

        System.setIn(new ByteArrayInputStream("4".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingController, times(1)).cancelBooking();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeFifthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingController).getAvailableSlotsByDate();

        System.setIn(new ByteArrayInputStream("5".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingController, times(1)).getAvailableSlotsByDate();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeSixthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingController).getAvailableSlotsByDate();

        System.setIn(new ByteArrayInputStream("6".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingController, times(1)).getAvailableSlotsByDate();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeSeventhMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingController).getBookingsByDate();

        System.setIn(new ByteArrayInputStream("7".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingController, times(1)).getBookingsByDate();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeEighthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingController).getBookingsByDate();

        System.setIn(new ByteArrayInputStream("8".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingController, times(1)).getBookingsByDate();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeNinthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingController).getBookingsByUserName();

        System.setIn(new ByteArrayInputStream("9".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingController, times(1)).getBookingsByUserName();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeTenthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingController).getBookingsByUserName();

        System.setIn(new ByteArrayInputStream("10".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingController, times(1)).getBookingsByUserName();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeEleventhMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingController).getAllBookings();

        System.setIn(new ByteArrayInputStream("11".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingController, times(1)).getAllBookings();
    }

    @Test
    @DisplayName("")
    void handleUserAction_whenInvokeTwelfthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingController).getAllBookings();

        System.setIn(new ByteArrayInputStream("12".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingController, times(1)).getAllBookings();
    }
}