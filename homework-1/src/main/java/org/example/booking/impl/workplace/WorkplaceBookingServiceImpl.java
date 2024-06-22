package org.example.booking.impl.workplace;

import org.example.booking.BookingRepository;
import org.example.booking.BookingService;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;
import org.example.exception.SlotValidationException;
import org.example.user.service.AuthenticationService;
import org.example.validation.TimeValidationService;
import org.example.workplace.model.Workplace;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WorkplaceBookingServiceImpl implements BookingService {

    private int id = 0;
    private final AuthenticationService authenticationService = ApplicationContext
            .getInstance().getAuthenticationService();
    private final BookingRepository workplaceBookingRepository = ApplicationContext
            .getInstance().getWorkplaceBookingRepository();
    private final TimeValidationService timeValidationService = ApplicationContext
            .getInstance().getTimeValidationService();
    private final CrudRepository<Workplace> workPlaceRepository = ApplicationContext
            .getInstance().getWorkPlaceRepository();

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
                    .placeType(PlaceType.WORKPLACE)
                    .bookerName(authenticationService.getAuthorizedUser().getUserName())
                    .bookingPlaceId(placeId)
                    .build();
            workplaceBookingRepository.save(booking);
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID  бронирования рабочего места");
        try {
            int workplaceId = scanner.nextInt();
            String userName = authenticationService.getAuthorizedUser().getUserName();
            workplaceBookingRepository.deleteBooking(workplaceId, userName);
        } catch (InputMismatchException exc) {
            System.out.println("ID рабочего места должен быть цифрой");
        }
    }

    @Override
    public void getAvailableSlotsByDate() {
        try {
            LocalDate date = getBookingDate();
            Map<Integer, List<Slot>> availableSlotsByPlaceId = new HashMap<>();
            List<Booking> bookingsByDate = workplaceBookingRepository.findBookingByDate(date);
            List<Integer> workPlaceIds = workPlaceRepository.findAll().stream()
                    .map(Workplace::getWorkplaceId)
                    .toList();

            for (Integer workplaceId : workPlaceIds) {
                availableSlotsByPlaceId.put(workplaceId, new ArrayList<>(Arrays.asList(Slot.MORNING_SLOT, Slot.AFTERNOON_SLOT)));
                for (Booking booking : bookingsByDate) {
                    if (booking.getBookingPlaceId().equals(workplaceId)) {
                        if (booking.getSlot().equals(Slot.MORNING_SLOT)) {
                            availableSlotsByPlaceId.get(workplaceId).remove(Slot.MORNING_SLOT);
                        } else if (booking.getSlot().equals(Slot.AFTERNOON_SLOT)) {
                            availableSlotsByPlaceId.get(workplaceId).remove(Slot.AFTERNOON_SLOT);
                        }
                    }
                }
            }
            System.out.println(String.format("Доступные слоты на %s, workplaceID=[slot]: %s : ", date, availableSlotsByPlaceId));
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void getBookingsByDate() {
        System.out.println("Брони на указанную дату: " + workplaceBookingRepository.findBookingByDate(getBookingDate()));
    }

    @Override
    public void getBookingsByUserName() {
        String userName = getUserName();
        System.out.println(
                String.format("Брони пользователя %s: %s ",
                        userName, workplaceBookingRepository.findBookingByUserName(userName)));
    }

    @Override
    public List<Booking> getAllBookings() {
        return workplaceBookingRepository.findAllBookings();
    }

    private Slot getSlot() throws InputMismatchException {
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

    private LocalDate getBookingDate() throws DateTimeParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату в формате гггг-мм-дд:");
        String inputDate = scanner.next();
        return LocalDate.parse(inputDate);
    }

    private void checkBookingConflict(Slot slot, LocalDate date, Integer placeId) {
        List<Booking> bookedSlotsByDate = workplaceBookingRepository.findBookingByDate(date);
        if (!bookedSlotsByDate.isEmpty()) {
            boolean isSlotBusy = bookedSlotsByDate.stream()
                    .anyMatch(booking -> booking.getSlot().equals(slot) && booking.getBookingPlaceId().equals(placeId));
            if (isSlotBusy) {
                throw new SlotValidationException("Извините, выбранный слот занят");
            }
        }
    }

    private Integer getPlaceId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID рабочего места");
        try {
            int workplaceId = scanner.nextInt();
            if (workPlaceRepository.existsById(workplaceId)) {
                return workplaceId;
            }
        } catch (InputMismatchException exc) {
            System.out.println("ID рабочего места должен быть цифрой");
        }
        throw new NoSuchElementException("рабочего места c введенным ID не существует");
    }

    private String getUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        return scanner.next();
    }
}