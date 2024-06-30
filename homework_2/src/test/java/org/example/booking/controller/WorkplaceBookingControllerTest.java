package org.example.booking.controller;

import org.example.booking.BookingService;
import org.example.booking.impl.workplace.WorkplaceBookingServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkplaceBookingControllerTest {

    private final WorkplaceBookingController controller = new WorkplaceBookingController();

    @Mock
    private BookingService workplaceBookingService = new WorkplaceBookingServiceImpl();

    @BeforeEach
    void setUp() {
        controller.setWorkplaceBookingService(workplaceBookingService);
    }

    @Test
    @DisplayName("Вызов метода создания бронирования рабочего места")
    void createBooking() {
        doNothing().when(workplaceBookingService).createBooking();

        controller.createBooking();

        verify(workplaceBookingService, times(1)).createBooking();
    }

    @Test
    @DisplayName("Вызов метода отмены бронирования рабочего места")
    void cancelBooking() {
        doNothing().when(workplaceBookingService).cancelBooking();

        controller.cancelBooking();

        verify(workplaceBookingService, times(1)).cancelBooking();
    }

    @Test
    @DisplayName("Вызов метода поиска свободных слотов бронирования рабочих мест")
    void getAvailableSlotsByDate() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getAvailableSlotsByDate(any());

        System.setIn(new ByteArrayInputStream("2024-07-10".getBytes()));

        assertDoesNotThrow(controller::getAvailableSlotsByDate);
        verify(workplaceBookingService, times(1)).getAvailableSlotsByDate(any());
    }

    @Test
    @DisplayName("Вызов метода поиска бронирований рабочих мест на дату")
    void getBookingsByDate() {
        doThrow(RuntimeException.class).when(workplaceBookingService).getBookingsByDate();

        assertDoesNotThrow(controller::getBookingsByDate);

        verify(workplaceBookingService, times(1)).getBookingsByDate();
    }

    @Test
    @DisplayName("Вызов метода поиска бронирований рабочих мест по имени")
    void getBookingsByUserName() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getBookingsByUserName();

        assertThatThrownBy(controller::getBookingsByUserName).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).getBookingsByUserName();
    }

    @Test
    @DisplayName("Вызов метода поиска всех бронирований рабочих мест")
    void getAllBookings() {
        doThrow(NoSuchElementException.class).when(workplaceBookingService).getAllBookings();

        assertThatThrownBy(controller::getAllBookings).isInstanceOf(NoSuchElementException.class);
        verify(workplaceBookingService, times(1)).getAllBookings();
    }

    @Test
    @DisplayName("Вызов метода поиска всех бронирований рабочих мест без исключений")
    void getAllBookings_doesNotThrowException() {
        when(workplaceBookingService.getAllBookings()).thenReturn(List.of(new Booking()));

        controller.getAllBookings();

        verify(workplaceBookingService, times(1)).getAllBookings();
    }
}