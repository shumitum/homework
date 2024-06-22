package org.example.booking.impl.workplace;

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
import java.util.Scanner;

public class WorkplaceBookingServiceImpl implements BookingService {

    private int id = 0;
    private final AuthenticationService authenticationService = ApplicationContext
            .getInstance().getAuthenticationService();
    private final BookingRepository workplaceBookingRepository = ApplicationContext
            .getInstance().getWorkplaceBookingRepository();

    @Override
    public void createBooking() {
        try {
            final Booking booking = Booking.builder()
                    .bookingId(++id)
                    .bookingDate(setBookingDate())
                    .slot(setSlot())
                    .placeType(PlaceType.WORKPLACE)
                    .bookerName(authenticationService.getAuthorizedUser().getUserName())
                    .bookingPlaceId(null)//!!!!
                    .build();
            workplaceBookingRepository.save(booking);
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private Slot setSlot() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите слот: цифра 1 - с 8 до 12 ч., цифра 2 с 13 до 17 ч.");
        int slot = scanner.nextInt();
        if (slot == 1) {
            return Slot.MORNING_SLOT;
        } else if (slot == 2) {
            return Slot.AFTERNOON_SLOT;
        }
        throw new InputMismatchException("Неверный код слота, цифра 1 - с 8 до 12 ч., цифра 2 с 13 до 17 ч.");
    }

    private LocalDate setBookingDate() throws DateTimeParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату бронирования в формате гггг-мм-дд:");
        String inputDate = scanner.next();
        return LocalDate.parse(inputDate);
    }
}
