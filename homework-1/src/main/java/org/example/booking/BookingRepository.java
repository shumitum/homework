package org.example.booking;

import org.example.booking.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    void save(Booking booking);

    void deleteBooking(Integer bookingId, String userName);

    List<Booking> findBookingByUserName(String userName);

    List<Booking> findBookingByDate(LocalDate date);

    List<Booking> findAllBookings();
}
