package org.example.booking.impl.conferencehall;

import lombok.AccessLevel;
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
import org.example.user.service.AuthenticationService;
import org.example.validation.TimeValidationService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Setter
public class ConferenceHallBookingServiceImpl implements BookingService {

    @Setter(AccessLevel.NONE)
    private int id;
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
            LocalDate date = getBookingDate();
            timeValidationService.checkBookingIsNotBeforeNow(date);
            Slot slot = getSlot();
            Integer placeId = getPlaceId();
            checkBookingConflict(slot, date, placeId);
            final Booking booking = Booking.builder()
                    .bookingId(++id)
                    .bookingDate(date)
                    .slot(slot)
                    .placeType(PlaceType.CONFERENCE_HALL)
                    .bookerName(authenticationService.getAuthorizedUser().getUserName())
                    .placeId(placeId)
                    .build();
            conferenceHallBookingRepository.save(booking);
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID бронирования конференц-зала");
        try {
            int conferenceHallId = scanner.nextInt();
            String userName = authenticationService.getAuthorizedUser().getUserName();
            conferenceHallBookingRepository.deleteBooking(conferenceHallId, userName);
        } catch (InputMismatchException exc) {
            System.out.println("ID конференц-зала должен быть цифрой");
        }
    }

    @Override
    public void getAvailableSlotsByDate() {
        try {
            LocalDate date = getBookingDate();
            Map<Integer, List<Slot>> availableSlotsByPlaceId = new HashMap<>();
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
            System.out.println(String.format("Доступные слоты на %s, conferenceHallID=[slot]: %s : ", date, availableSlotsByPlaceId));
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void getBookingsByDate() {
        System.out.println("Брони на указанную дату: " + conferenceHallBookingRepository.findBookingByDate(getBookingDate()));
    }

    @Override
    public void getBookingsByUserName() {
        String userName = getUserName();
        System.out.println(
                String.format("Брони пользователя %s: %s ",
                        userName, conferenceHallBookingRepository.findBookingByUserName(userName)));
    }

    @Override
    public List<Booking> getAllBookings() {
        return conferenceHallBookingRepository.findAllBookings();
    }

    private Slot getSlot() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите слот: цифра 1 - с 9 до 13 ч., цифра 2 - с 14 до 18 ч.");
        int slot = scanner.nextInt();
        if (slot == 1) {
            return Slot.MORNING_SLOT;
        } else if (slot == 2) {
            return Slot.AFTERNOON_SLOT;
        }
        throw new InputMismatchException("Неверный код слота, цифра 1 - с 9 до 13 ч., цифра 2 - с 14 до 18 ч.");
    }

    private LocalDate getBookingDate() throws DateTimeParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату в формате гггг-мм-дд:");
        String inputDate = scanner.next();
        return LocalDate.parse(inputDate);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID конференц-зала");
        try {
            int conferenceHallId = scanner.nextInt();
            if (conferenceHallRepository.existsById(conferenceHallId)) {
                return conferenceHallId;
            }
        } catch (InputMismatchException exc) {
            System.out.println("ID конференц-зала должен быть цифрой");
        }
        throw new NoSuchElementException("конференц-зала c введенным ID не существует");
    }

    private String getUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        return scanner.next();
    }
}