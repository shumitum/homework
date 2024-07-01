package org.example.booking.impl.workplace;

import lombok.Setter;
import org.example.booking.BookingRepository;
import org.example.booking.BookingService;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.context.ApplicationContext;
import org.example.crud.CrudRepository;
import org.example.exception.SlotValidationException;
import org.example.in.UserInput;
import org.example.out.Output;
import org.example.user.service.AuthenticationService;
import org.example.validation.TimeValidationService;
import org.example.workplace.model.Workplace;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Setter
public class WorkplaceBookingServiceImpl implements BookingService {

    private ApplicationContext context;
    private AuthenticationService authenticationService;
    private BookingRepository workplaceBookingRepository;
    private TimeValidationService timeValidationService;
    private CrudRepository<Workplace> workPlaceRepository;

    public WorkplaceBookingServiceImpl() {
        this.context = ApplicationContext.getInstance();
        this.authenticationService = context.getAuthenticationService();
        this.workplaceBookingRepository = context.getWorkplaceBookingRepository();
        this.timeValidationService = context.getTimeValidationService();
        this.workPlaceRepository = context.getWorkPlaceRepository();
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
                    .placeType(PlaceType.WORKPLACE)
                    .bookerName(authenticationService.getAuthorizedUser().getUserName())
                    .placeId(placeId)
                    .build();
            workplaceBookingRepository.save(booking);
        } catch (RuntimeException exc) {
            Output.printMessage(exc.getMessage());
        }
    }

    @Override
    public void cancelBooking() {
        try {
            int workplaceId = UserInput.digitInput("Введите ID  бронирования рабочего места");
            String userName = authenticationService.getAuthorizedUser().getUserName();
            workplaceBookingRepository.deleteBooking(workplaceId, userName);
        } catch (InputMismatchException exc) {
            Output.printMessage("ID рабочего места должен быть цифрой");
        }
    }

    @Override
    public Map<Integer, List<Slot>> getAvailableSlotsByDate(LocalDate date) {
        Map<Integer, List<Slot>> availableSlotsByPlaceId = new HashMap<>();
        try {
            List<Booking> bookingsByDate = workplaceBookingRepository.findBookingByDate(date);
            List<Integer> workPlaceIds = workPlaceRepository.findAll().stream()
                    .map(Workplace::getWorkplaceId)
                    .toList();

            for (Integer workplaceId : workPlaceIds) {
                availableSlotsByPlaceId.put(workplaceId, new ArrayList<>(Arrays.asList(Slot.MORNING_SLOT, Slot.AFTERNOON_SLOT)));
                for (Booking booking : bookingsByDate) {
                    if (booking.getPlaceId().equals(workplaceId)) {
                        if (booking.getSlot().equals(Slot.MORNING_SLOT)) {
                            availableSlotsByPlaceId.get(workplaceId).remove(Slot.MORNING_SLOT);
                        } else if (booking.getSlot().equals(Slot.AFTERNOON_SLOT)) {
                            availableSlotsByPlaceId.get(workplaceId).remove(Slot.AFTERNOON_SLOT);
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
        return workplaceBookingRepository.findBookingByDate(date);
    }

    @Override
    public List<Booking> getBookingsByUserName() {
        String userName = UserInput.stringInput("Введите имя пользователя:");
        return workplaceBookingRepository.findBookingByUserName(userName);
    }

    @Override
    public List<Booking> getAllBookings() {
        return workplaceBookingRepository.findAllBookings();
    }

    private void checkBookingConflict(Slot slot, LocalDate date, Integer placeId) {
        List<Booking> bookedSlotsByDate = workplaceBookingRepository.findBookingByDate(date);
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
            int workplaceId = UserInput.digitInput("Введите ID рабочего места");
            if (workPlaceRepository.existsById(workplaceId)) {
                return workplaceId;
            }
        } catch (InputMismatchException exc) {
            throw new InputMismatchException("ID рабочего места должен быть цифрой");
        }
        throw new NoSuchElementException("рабочего места c введенным ID не существует");
    }

    private Slot getSlot() throws InputMismatchException {
        try {
            int slot = UserInput.digitInput("Выберите слот: цифра 1 - с 8 до 12 ч., цифра 2 с 13 до 17 ч.");
            if (slot == 1) {
                return Slot.MORNING_SLOT;
            } else if (slot == 2) {
                return Slot.AFTERNOON_SLOT;
            }
        } catch (InputMismatchException exc) {
            throw new InputMismatchException("ID слота должен быть цифрой");
        }
        throw new InputMismatchException("Неверный код слота, цифра 1 - с 8 до 12 ч., цифра 2 с 13 до 17 ч.");
    }
}