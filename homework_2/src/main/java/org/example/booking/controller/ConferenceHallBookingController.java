package org.example.booking.controller;

import lombok.Setter;
import org.example.booking.BookingService;
import org.example.booking.model.Booking;
import org.example.booking.model.Slot;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.out.Output;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Conference Hall Booking Controller предоставляет доступ к функционалу бронирования конференц-залов.
 * Осуществляет вывод результата.
 */
@Setter
public class ConferenceHallBookingController {

    /**
     * Booking Service предоставляет функционалу для бронирования конференц-залов.
     */
    private BookingService conferenceHallBookingService;

    public ConferenceHallBookingController() {
        this.conferenceHallBookingService = ApplicationContext.getInstance().getConferenceHallBookingService();
    }

    /**
     * Создает новое бронирование конференц-зала.
     */
    public void createBooking() {
        conferenceHallBookingService.createBooking();
    }

    /**
     * Отменяет существующее бронирование конференц-зала.
     */
    public void cancelBooking() {
        conferenceHallBookingService.cancelBooking();
    }

    /**
     * Позволяет получить свободные для бронирования слоты на конкретную дату.
     */
    public void getAvailableSlotsByDate() {
        try {
            LocalDate date = UserInput.dateInput();
            Map<Integer, List<Slot>> availableSlotsByDate = conferenceHallBookingService.getAvailableSlotsByDate(date);
            Output.printMessage(String.format("Доступные слоты на %s, conferenceHallID=[slot]: %s", date, availableSlotsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    /**
     * Позволяет получить список бронирований конференц-залов на конкретную дату.
     */
    public void getBookingsByDate() {
        try {
            List<Booking> bookingsByDate = conferenceHallBookingService.getBookingsByDate();
            Output.printMessage(String.format("Брони конференц-залов на указанную дату: %s", bookingsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    /**
     * Позволяет получить список бронирований конференц-залов по имени бронировавшего.
     */
    public void getBookingsByUserName() {
        List<Booking> bookingsByUserName = conferenceHallBookingService.getBookingsByUserName();
        Output.printMessage(String.format("Брони конференц-залов пользователя: %s", bookingsByUserName));
    }

    /**
     * Позволяет получить список всех бронирований конференц-залов.
     */
    public void getAllBookings() {
        List<Booking> bookings = conferenceHallBookingService.getAllBookings();
        Output.printMessage("Список всех бронирований конференц-залов: ");
        if (bookings.isEmpty()) {
            Output.printMessage("Бронирования отсутствуют.");
        } else {
            bookings.forEach(booking -> Output.printMessage(booking.toString()));
        }
    }
}
