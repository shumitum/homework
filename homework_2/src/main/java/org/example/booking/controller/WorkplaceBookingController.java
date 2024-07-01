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
 * Workplace Booking Controller предоставляет доступ к функционалу бронирования рабочих мест.
 * Осуществляет вывод результата.
 */
@Setter
public class WorkplaceBookingController {

    /**
     * Booking Service предоставляет функционалу для бронирования рабочих мест.
     */
    private BookingService workplaceBookingService;

    public WorkplaceBookingController() {
        this.workplaceBookingService = ApplicationContext.getInstance().getWorkplaceBookingService();
    }

    /**
     * Создает новое бронирование рабочего места.
     */
    public void createBooking() {
        workplaceBookingService.createBooking();
    }

    /**
     * Отменяет существующее бронирование рабочего места.
     */
    public void cancelBooking() {
        workplaceBookingService.cancelBooking();
    }

    /**
     * Позволяет получить свободные для бронирования слоты на конкретную дату.
     */
    public void getAvailableSlotsByDate() {
        try {
            LocalDate date = UserInput.dateInput();
            Map<Integer, List<Slot>> availableSlotsByDate = workplaceBookingService.getAvailableSlotsByDate(date);
            Output.printMessage(String.format("Доступные слоты на %s, workplaceID=[slot]: %s", date, availableSlotsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    /**
     * Позволяет получить список бронирований рабочих мест на конкретную дату.
     */
    public void getBookingsByDate() {
        try {
            List<Booking> bookingsByDate = workplaceBookingService.getBookingsByDate();
            Output.printMessage(String.format("Брони рабочих мест на указанную дату: %s", bookingsByDate));
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    /**
     * Позволяет получить список бронирований рабочих мест по имени бронировавшего.
     */
    public void getBookingsByUserName() {
        List<Booking> bookingsByUserName = workplaceBookingService.getBookingsByUserName();
        Output.printMessage(String.format("Брони рабочих мест пользователя: %s", bookingsByUserName));
    }

    /**
     * Позволяет получить список всех бронирований рабочих мест.
     */
    public void getAllBookings() {
        List<Booking> bookings = workplaceBookingService.getAllBookings();
        Output.printMessage("Список всех бронирований рабочих мест: ");
        if (bookings.isEmpty()) {
            Output.printMessage("Бронирования отсутствуют.");
        } else {
            bookings.forEach(booking -> Output.printMessage(booking.toString()));
        }
    }
}
