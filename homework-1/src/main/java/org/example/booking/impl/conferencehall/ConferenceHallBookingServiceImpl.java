package org.example.booking.impl.conferencehall;

import org.example.booking.BookingRepository;
import org.example.booking.BookingService;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.context.ApplicationContext;
import org.example.user.service.AuthenticationService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConferenceHallBookingServiceImpl implements BookingService {

    private int id = 0;
    private final AuthenticationService authenticationService = ApplicationContext
            .getInstance().getAuthenticationService();
    private final BookingRepository conferenceHallBookingRepository = ApplicationContext
            .getInstance().getConferenceHallBookingRepository();

    @Override
    public void createBooking() {
        try {
            final Booking booking = Booking.builder()
                    .bookingId(++id)
                    .bookingDate(setBookingDate())
                    .slot(setSlot())
                    .placeType(PlaceType.WORKPLACE)
                    .bookerName(authenticationService.getAuthorizedUser().getUserName())
                    .bookingPlaceId(null)//!!!!!!!!!
                    .build();
            conferenceHallBookingRepository.save(booking);
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void cancelBooking() {

    }

    @Override
    public void getAvailableSlotsByDate() {

    }

    @Override
    public void getBookingsByDate() {

    }

    @Override
    public void getBookingsByUserName() {

    }

    @Override
    public List<Booking> getAllBookings() {
        return conferenceHallBookingRepository.findAllBookings();
    }

    private Slot setSlot() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите слот: цифра 1 - с 8 до 14 ч., цифра 2 с 15 до 20 ч.");
        int slot = scanner.nextInt();
        if (slot == 1) {
            return Slot.MORNING_SLOT;
        } else if (slot == 2) {
            return Slot.AFTERNOON_SLOT;
        }
        throw new InputMismatchException("Неверный код слота, цифра 1 - с 8 до 14 ч., цифра 2 с 15 до 20 ч.");
    }

    private LocalDate setBookingDate() throws DateTimeParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату бронирования в формате гггг-мм-дд:");
        String inputDate = scanner.next();
        return LocalDate.parse(inputDate);
    }
}
