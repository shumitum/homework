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

public class WorkplaceBookingController {

    private BookingService workplaceBookingService;

    public WorkplaceBookingController() {
        this.workplaceBookingService = ApplicationContext.getInstance().getWorkplaceBookingService();
    }

    public void createBooking() {
        try {
            workplaceBookingService.createBooking();
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    public void cancelBooking() {
        workplaceBookingService.cancelBooking();
    }

    public void getAvailableSlotsByDate() {
        try {
            LocalDate date = UserInput.dateInput();
            Map<Integer, List<Slot>> availableSlotsByDate = workplaceBookingService.getAvailableSlotsByDate(date);
            Output.printMessage(String.format("Доступные слоты на %s, workplaceID=[slot]: %s", date, availableSlotsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    public void getBookingsByDate() {
        try {
            List<Booking> bookingsByDate = workplaceBookingService.getBookingsByDate();
            Output.printMessage(String.format("Брони рабочих мест на указанную дату: %s", bookingsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    public void getBookingsByUserName() {
        List<Booking> bookingsByUserName = workplaceBookingService.getBookingsByUserName();
        Output.printMessage(String.format("Брони рабочих мест пользователя: %s", bookingsByUserName));
    }

    public void getAllBookings() {
        try {
            Output.printMessage("Список всех бронирований рабочих мест: " + workplaceBookingService.getAllBookings());
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }
}
