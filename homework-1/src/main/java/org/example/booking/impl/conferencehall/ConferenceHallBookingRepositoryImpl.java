package org.example.booking.impl.conferencehall;

import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;

import java.time.LocalDate;
import java.util.*;

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

    @Override
    public void deleteBooking(Integer bookingId, String userName) {
        conferenceHallBookings.remove(bookingId);

    }

    @Override
    public List<Booking> findBookingByUserName(String userName) {
        return List.of();
    }


    @Override
    public List<Booking> findBookingByDate(LocalDate date) {
        return List.of();
    }

    //@Override
    //public Optional<Booking> findBookingByDateAndPlaceId(LocalDate date) {
    //    return Optional.empty();
    //}


    @Override
    public List<Booking> findAllBookings() {
        return conferenceHallBookings.values().stream()
                .sorted(Comparator.comparing(Booking::getBookingDate))
                .toList();
    }

}
