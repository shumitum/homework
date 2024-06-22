package org.example.booking.impl.conferencehall;

import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConferenceHallBookingRepositoryImpl implements BookingRepository {

    private final Map<Integer, Booking> conferenceHallBookings = new HashMap<>();

    @Override
    public void save(Booking booking) {
        if (booking != null) {
            conferenceHallBookings.put(booking.getBookingId(), booking);
            System.out.printf("Бронирование конференц-зала создано - %s%n%n", booking);
        } else {
            System.out.println("Бронирование конференц-зала is null, бронирование wasn't saved");
        }
    }

    @Override
    public void deleteBooking(Integer bookingId, String userName) {
        if (conferenceHallBookings.containsKey(bookingId)) {
            if (conferenceHallBookings.get(bookingId).getBookerName().equals(userName)) {
                conferenceHallBookings.remove(bookingId);
                System.out.println(String.format("Бронирование конференц-зала с ID=%s  отменено", bookingId));
            } else {
                System.out.println("Только владелец может отменить бронирование");
            }
        } else {
            System.out.println("Бронирования с указанным ID не существует");
        }
    }

    @Override
    public List<Booking> findBookingByUserName(String userName) {
        return conferenceHallBookings.values().stream()
                .filter(booking -> booking.getBookerName().equals(userName))
                .toList();
    }

    @Override
    public List<Booking> findBookingByDate(LocalDate date) {
        return conferenceHallBookings.values().stream()
                .filter(booking -> booking.getBookingDate().equals(date))
                .toList();
    }

    @Override
    public List<Booking> findAllBookings() {
        return conferenceHallBookings.values().stream()
                .toList();
    }
}
