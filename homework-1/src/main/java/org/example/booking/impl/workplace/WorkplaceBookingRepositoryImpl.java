package org.example.booking.impl.workplace;

import lombok.Getter;
import lombok.Setter;
import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class WorkplaceBookingRepositoryImpl implements BookingRepository {

    private Map<Integer, Booking> workPlaceBookings = new HashMap<>();

    @Override
    public void save(Booking booking) {
        if (booking != null && !workPlaceBookings.containsKey(booking.getBookingId())) {
            workPlaceBookings.put(booking.getBookingId(), booking);
            System.out.printf("Бронирование рабочего места создано - %s%n%n", booking);
        } else {
            System.out.println("Бронирование рабочего места is null or have existing id, бронирование wasn't saved");
        }
    }

    @Override
    public void deleteBooking(Integer bookingId, String userName) {
        if (workPlaceBookings.containsKey(bookingId)) {
            if (workPlaceBookings.get(bookingId).getBookerName().equals(userName)) {
                workPlaceBookings.remove(bookingId);
                System.out.println(String.format("Бронирование рабочего места с ID=%s  отменено", bookingId));
            } else {
                System.out.println("Только владелец может отменить бронирование");
            }
        } else {
            System.out.println("Бронирования с указанным ID не существует");
        }
    }

    @Override
    public List<Booking> findBookingByUserName(String userName) {
        return workPlaceBookings.values().stream()
                .filter(booking -> booking.getBookerName().equals(userName))
                .toList();
    }

    @Override
    public List<Booking> findBookingByDate(LocalDate date) {
        return workPlaceBookings.values().stream()
                .filter(booking -> booking.getBookingDate().equals(date))
                .toList();
    }

    @Override
    public List<Booking> findAllBookings() {
        return workPlaceBookings.values().stream()
                .toList();
    }
}

