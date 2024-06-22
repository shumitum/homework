package org.example.booking.impl.conferencehall;

import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ConferenceHallBookingRepositoryImpl implements BookingRepository {

    private final Map<LocalDate, Booking> conferenceHallBookings = new HashMap<>();

    @Override
    public void save(Booking booking) {
        if (booking != null) {
            conferenceHallBookings.put(booking.getBookingDate(), booking);
            System.out.printf("Бронирование конференц-зала создано - %s%n%n", booking);
        } else {
            System.out.println("Бронирование конференц-зала is null, бронирование wasn't saved");
        }
    }

}
