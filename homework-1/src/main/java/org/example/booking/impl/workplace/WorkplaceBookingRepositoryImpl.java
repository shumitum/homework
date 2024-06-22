package org.example.booking.impl.workplace;

import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WorkplaceBookingRepositoryImpl implements BookingRepository {

    private final Map<LocalDate, Booking> workPlaceBookings = new HashMap<>();

    @Override
    public void save(Booking booking) {
        if (booking != null) {
            workPlaceBookings.put(booking.getBookingDate(), booking);
            System.out.printf("Бронирование рабочего места создано - %s%n%n", booking);
        } else {
            System.out.println("Бронирование рабочего места is null, бронирование wasn't saved");
        }
    }


}

