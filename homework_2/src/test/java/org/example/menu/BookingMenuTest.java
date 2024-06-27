package org.example.menu;

import org.example.booking.BookingService;
import org.example.booking.impl.conferencehall.ConferenceHallBookingServiceImpl;
import org.example.booking.impl.workplace.WorkplaceBookingServiceImpl;
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
class BookingMenuTest {

    private final BookingMenu bookingMenu = new BookingMenu();

    @Mock
    private final BookingService conferenceHallBookingService = new ConferenceHallBookingServiceImpl();

    @Mock
    private final BookingService workplaceBookingService = new WorkplaceBookingServiceImpl();

    @BeforeEach
    void setUp() {
        bookingMenu.setConferenceHallBookingService(conferenceHallBookingService);
        bookingMenu.setWorkplaceBookingService(workplaceBookingService);
    }

    @Test
    void handleUserAction_whenInvokeFirstMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).createBooking();

        System.setIn(new ByteArrayInputStream("1".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).createBooking();
    }

    @Test
    void handleUserAction_whenInvokeSecondMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).createBooking();

        System.setIn(new ByteArrayInputStream("2".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).createBooking();
    }

    @Test
    void handleUserAction_whenInvokeThirdMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).cancelBooking();

        System.setIn(new ByteArrayInputStream("3".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).cancelBooking();
    }

    @Test
    void handleUserAction_whenInvokeFourthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).cancelBooking();

        System.setIn(new ByteArrayInputStream("4".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).cancelBooking();
    }

    @Test
    void handleUserAction_whenInvokeFifthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getAvailableSlotsByDate();

        System.setIn(new ByteArrayInputStream("5".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).getAvailableSlotsByDate();
    }

    @Test
    void handleUserAction_whenInvokeSixthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getAvailableSlotsByDate();

        System.setIn(new ByteArrayInputStream("6".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).getAvailableSlotsByDate();
    }

    @Test
    void handleUserAction_whenInvokeSeventhMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getBookingsByDate();

        System.setIn(new ByteArrayInputStream("7".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).getBookingsByDate();
    }

    @Test
    void handleUserAction_whenInvokeEighthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getBookingsByDate();

        System.setIn(new ByteArrayInputStream("8".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).getBookingsByDate();
    }

    @Test
    void handleUserAction_whenInvokeNinthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getBookingsByUserName();

        System.setIn(new ByteArrayInputStream("9".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).getBookingsByUserName();
    }

    @Test
    void handleUserAction_whenInvokeTenthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getBookingsByUserName();

        System.setIn(new ByteArrayInputStream("10".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).getBookingsByUserName();
    }

    @Test
    void handleUserAction_whenInvokeEleventhMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getAllBookings();

        System.setIn(new ByteArrayInputStream("11".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).getAllBookings();
    }

    @Test
    void handleUserAction_whenInvokeTwelfthMenuOption_thenThrowsException() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getAllBookings();

        System.setIn(new ByteArrayInputStream("12".getBytes()));

        assertThatThrownBy(bookingMenu::handleUserAction).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).getAllBookings();
    }
}