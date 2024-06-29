package org.example.booking.controller;

import org.example.booking.BookingService;
import org.example.booking.model.Booking;
import org.example.booking.model.Slot;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.out.Output;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ConferenceHallBookingController {

    private BookingService conferenceHallBookingService;

    public ConferenceHallBookingController() {
        try {
            this.conferenceHallBookingService = ApplicationContext.getInstance().getConferenceHallBookingService();
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    public void createBooking() {
        conferenceHallBookingService.createBooking();
    }

    public void cancelBooking() {
        conferenceHallBookingService.cancelBooking();
    }

    public void getAvailableSlotsByDate() {
        try {
            LocalDate date = UserInput.dateInput();
            Map<Integer, List<Slot>> availableSlotsByDate = conferenceHallBookingService.getAvailableSlotsByDate(date);
            Output.printMessage(String.format("Доступные слоты на %s, conferenceHallID=[slot]: %s", date, availableSlotsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    public void getBookingsByDate() {
        try {
            List<Booking> bookingsByDate = conferenceHallBookingService.getBookingsByDate();
            Output.printMessage(String.format("Брони конференц-залов на указанную дату: %s", bookingsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    public void getBookingsByUserName() {
        List<Booking> bookingsByUserName = conferenceHallBookingService.getBookingsByUserName();
        Output.printMessage(String.format("Брони конференц-залов пользователя: %s", bookingsByUserName));
    }

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
