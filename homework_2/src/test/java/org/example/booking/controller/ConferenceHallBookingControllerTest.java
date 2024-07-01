package org.example.booking.controller;

import org.example.booking.BookingService;
import org.example.booking.impl.conferencehall.ConferenceHallBookingServiceImpl;
import org.example.booking.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConferenceHallBookingControllerTest {

    private final ConferenceHallBookingController controller = new ConferenceHallBookingController();

    @Mock
    private BookingService conferenceHallBookingService = new ConferenceHallBookingServiceImpl();

    @BeforeEach
    void setUp() {
        controller.setConferenceHallBookingService(conferenceHallBookingService);
    }

    @Test
    @DisplayName("Вызов метода создания бронирования конф-зала")
    void createBooking() {
        doNothing().when(conferenceHallBookingService).createBooking();

        controller.createBooking();

        verify(conferenceHallBookingService, times(1)).createBooking();
    }

    @Test
    @DisplayName("Вызов метода отмены бронирования конф-зала")
    void cancelBooking() {
        doNothing().when(conferenceHallBookingService).cancelBooking();

        controller.cancelBooking();

        verify(conferenceHallBookingService, times(1)).cancelBooking();
    }

    @Test
    @DisplayName("Вызов метода поиска свободных слотов бронирования конф-залов")
    void getAvailableSlotsByDate() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getAvailableSlotsByDate(any());

        System.setIn(new ByteArrayInputStream("2024-07-10".getBytes()));

        assertDoesNotThrow(controller::getAvailableSlotsByDate);
        verify(conferenceHallBookingService, times(1)).getAvailableSlotsByDate(any());
    }

    @Test
    @DisplayName("Вызов метода поиска бронирований конф-залов на дату")
    void getBookingsByDate() {
        doThrow(RuntimeException.class).when(conferenceHallBookingService).getBookingsByDate();

        assertDoesNotThrow(controller::getBookingsByDate);

        verify(conferenceHallBookingService, times(1)).getBookingsByDate();
    }

    @Test
    @DisplayName("Вызов метода поиска бронирований конф-залов по имени")
    void getBookingsByUserName() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getBookingsByUserName();

        assertThatThrownBy(controller::getBookingsByUserName).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).getBookingsByUserName();
    }

    @Test
    @DisplayName("Вызов метода поиска всех бронирований конф-залов")
    void getAllBookings() {
        doThrow(NoSuchElementException.class).when(conferenceHallBookingService).getAllBookings();

        assertThatThrownBy(controller::getAllBookings).isInstanceOf(NoSuchElementException.class);
        verify(conferenceHallBookingService, times(1)).getAllBookings();
    }

    @Test
    @DisplayName("Вызов метода поиска всех бронирований конф-залов без исключений")
    void getAllBookings_doesNotThrowException() {
        when(conferenceHallBookingService.getAllBookings()).thenReturn(List.of(new Booking()));

        controller.getAllBookings();

        verify(conferenceHallBookingService, times(1)).getAllBookings();
    }
}