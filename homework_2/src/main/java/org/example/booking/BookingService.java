package org.example.booking;

import org.example.booking.model.Booking;
import org.example.booking.model.Slot;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    void createBooking();

    void cancelBooking();

    Map<Integer, List<Slot>> getAvailableSlotsByDate(LocalDate date);

    List<Booking> getBookingsByDate();

    List<Booking> getBookingsByUserName();

    List<Booking> getAllBookings();

}
