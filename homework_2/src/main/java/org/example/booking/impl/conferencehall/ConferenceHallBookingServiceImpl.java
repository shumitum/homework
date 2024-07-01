package org.example.booking.impl.conferencehall;

import lombok.Setter;
import org.example.booking.BookingRepository;
import org.example.booking.BookingService;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.conferencehall.model.ConferenceHall;
import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;
import org.example.exception.SlotValidationException;
import org.example.in.UserInput;
import org.example.out.Output;
import org.example.user.service.AuthenticationService;
import org.example.validation.TimeValidationService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Setter
public class ConferenceHallBookingServiceImpl implements BookingService {

    private ApplicationContext context;
    private AuthenticationService authenticationService;
    private BookingRepository conferenceHallBookingRepository;
    private TimeValidationService timeValidationService;
    private CrudRepository<ConferenceHall> conferenceHallRepository;

    public ConferenceHallBookingServiceImpl() {
        this.context = ApplicationContext.getInstance();
        this.authenticationService = context.getAuthenticationService();
        this.conferenceHallBookingRepository = context.getConferenceHallBookingRepository();
        this.timeValidationService = context.getTimeValidationService();
        this.conferenceHallRepository = context.getConferenceHallRepository();
    }

    @Override
    public void createBooking() {
        try {
            LocalDate date = UserInput.dateInput();
            timeValidationService.checkBookingIsNotBeforeNow(date);
            Slot slot = getSlot();
            Integer placeId = getPlaceId();
            checkBookingConflict(slot, date, placeId);
            final Booking booking = Booking.builder()
                    .bookingDate(date)
                    .slot(slot)
                    .placeType(PlaceType.CONFERENCE_HALL)
                    .bookerName(authenticationService.getAuthorizedUser().getUserName())
                    .placeId(placeId)
                    .build();
            conferenceHallBookingRepository.save(booking);
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    @Override
    public void cancelBooking() {
        try {
            int conferenceHallId = UserInput.digitInput("Введите ID бронирования конференц-зала");
            String userName = authenticationService.getAuthorizedUser().getUserName();
            conferenceHallBookingRepository.deleteBooking(conferenceHallId, userName);
        } catch (InputMismatchException exc) {
            Output.printMessage("ID конференц-зала должен быть цифрой");
        }
    }

    @Override
    public Map<Integer, List<Slot>> getAvailableSlotsByDate(LocalDate date) {
        Map<Integer, List<Slot>> availableSlotsByPlaceId = new HashMap<>();
        try {
            List<Booking> bookingsByDate = conferenceHallBookingRepository.findBookingByDate(date);
            List<Integer> conferenceHallIds = conferenceHallRepository.findAll().stream()
                    .map(ConferenceHall::getConferenceHallId)
                    .toList();

            for (Integer conferenceHallId : conferenceHallIds) {
                availableSlotsByPlaceId.put(conferenceHallId, new ArrayList<>(Arrays.asList(Slot.MORNING_SLOT, Slot.AFTERNOON_SLOT)));
                for (Booking booking : bookingsByDate) {
                    if (booking.getPlaceId().equals(conferenceHallId)) {
                        if (booking.getSlot().equals(Slot.MORNING_SLOT)) {
                            availableSlotsByPlaceId.get(conferenceHallId).remove(Slot.MORNING_SLOT);
                        } else if (booking.getSlot().equals(Slot.AFTERNOON_SLOT)) {
                            availableSlotsByPlaceId.get(conferenceHallId).remove(Slot.AFTERNOON_SLOT);
                        }
                    }
                }
            }
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
        return availableSlotsByPlaceId;
    }

    @Override
    public List<Booking> getBookingsByDate() throws DateTimeParseException {
        LocalDate date = UserInput.dateInput();
        return conferenceHallBookingRepository.findBookingByDate(date);
    }

    @Override
    public List<Booking> getBookingsByUserName() {
        String userName = UserInput.stringInput("Введите имя пользователя:");
        return conferenceHallBookingRepository.findBookingByUserName(userName);
    }

    @Override
    public List<Booking> getAllBookings() {
        return conferenceHallBookingRepository.findAllBookings();
    }

    private void checkBookingConflict(Slot slot, LocalDate date, Integer placeId) {
        List<Booking> bookedSlotsByDate = conferenceHallBookingRepository.findBookingByDate(date);
        if (!bookedSlotsByDate.isEmpty()) {
            boolean isSlotBusy = bookedSlotsByDate.stream()
                    .anyMatch(booking -> booking.getSlot().equals(slot) && booking.getPlaceId().equals(placeId));
            if (isSlotBusy) {
                throw new SlotValidationException("Извините, выбранный слот занят");
            }
        }
    }

    private Integer getPlaceId() {
        try {
            int conferenceHallId = UserInput.digitInput("Введите ID конференц-зала");
            if (conferenceHallRepository.existsById(conferenceHallId)) {
                return conferenceHallId;
            }
        } catch (InputMismatchException exc) {
            throw new InputMismatchException("ID конференц-зала должен быть цифрой");
        }
        throw new NoSuchElementException("конференц-зала c введенным ID не существует");
    }

    private Slot getSlot() throws InputMismatchException {
        try {
            int slot = UserInput.digitInput("Выберите слот: цифра 1 - с 9 до 13 ч., цифра 2 - с 14 до 18 ч.");
            if (slot == 1) {
                return Slot.MORNING_SLOT;
            } else if (slot == 2) {
                return Slot.AFTERNOON_SLOT;
            }
        } catch (InputMismatchException exc) {
            throw new InputMismatchException("ID слота должен быть цифрой");
        }
        throw new InputMismatchException("Неверный код слота, цифра 1 - с 9 до 13 ч., цифра 2 - с 14 до 18 ч.");
    }
}