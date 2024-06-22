package org.example.booking;

import org.example.booking.model.Booking;

import java.util.List;

public interface BookingService {
    void createBooking();

    void cancelBooking();

    void getAvailableSlotsByDate();

    void getBookingsByDate();

    void getBookingsByUserName();

    List<Booking> getAllBookings();

}
