package org.example.booking.impl.workplace;

import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.crud.CrudRepository;
import org.example.user.model.User;
import org.example.user.service.AuthenticationService;
import org.example.user.service.impl.AuthenticationServiceImpl;
import org.example.workplace.impl.WorkPlaceRepositoryImpl;
import org.example.workplace.model.Workplace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkplaceBookingServiceImplTest {

    private final WorkplaceBookingServiceImpl workplaceBookingService = new WorkplaceBookingServiceImpl();

    @Mock
    private CrudRepository<Workplace> workPlaceRepository = new WorkPlaceRepositoryImpl();

    @Mock
    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @Mock
    private BookingRepository workplaceBookingRepository = new WorkplaceBookingRepositoryImpl();

    private Booking booking;

    @BeforeEach
    void setUp() {
        workplaceBookingService.setAuthenticationService(authenticationService);
        workplaceBookingService.setWorkplaceBookingRepository(workplaceBookingRepository);
        workplaceBookingService.setWorkPlaceRepository(workPlaceRepository);
        booking = Booking.builder()
                .bookingId(1)
                .slot(Slot.AFTERNOON_SLOT)
                .bookingDate(LocalDate.of(2024, 7, 25))
                .placeType(PlaceType.WORKPLACE)
                .bookerName("name")
                .placeId(1)
                .build();
    }

    @Test
    @DisplayName("Отмена бронирования")
    void cancelBooking_whenInvoke_thenInvokeMethodsGetAuthorizedUserAndDeleteBooking() {
        when(authenticationService.getAuthorizedUser()).thenReturn(new User(1, "name", "123"));
        doNothing().when(workplaceBookingRepository).deleteBooking(1, "name");
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        workplaceBookingService.cancelBooking();
        verify(authenticationService, times(1)).getAuthorizedUser();
        verify(workplaceBookingRepository, times(1)).deleteBooking(1, "name");
    }

    @Test
    @DisplayName("Поиск свободных слотов на дату")
    void getAvailableSlotsByDate_whenInvoke_thenInvokeMethodsFindBookingByDateAndFindAll() {
        LocalDate date = LocalDate.of(2024, 7, 25);
        when(workplaceBookingRepository.findBookingByDate(date)).thenReturn(List.of(booking));
        when(workPlaceRepository.findAll()).thenReturn(List.of(new Workplace(1, 1)));


        System.setIn(new ByteArrayInputStream("2024-07-25".getBytes()));
        Map<Integer, List<Slot>> availableSlotsByDate = workplaceBookingService
                .getAvailableSlotsByDate(date);

        verify(workplaceBookingRepository, times(1)).findBookingByDate(date);
        verify(workPlaceRepository, times(1)).findAll();
        assertThat(availableSlotsByDate)
                .isNotEmpty()
                .hasSize(1)
                .containsEntry(1, List.of(Slot.MORNING_SLOT));
    }

    @Test
    @DisplayName("Поиск всех бронирований на определенную дату")
    void getBookingsByDate_whenInvokeWithValidDate_thenInvokeFindBookingByDateMethodFromRepository() {
        LocalDate date = LocalDate.of(2024, 7, 25);
        when(workplaceBookingRepository.findBookingByDate(date)).thenReturn(List.of(new Booking()));

        System.setIn(new ByteArrayInputStream("2024-07-25".getBytes()));
        List<Booking> bookingsByDate = workplaceBookingService.getBookingsByDate();

        verify(workplaceBookingRepository, times(1)).findBookingByDate(date);
        assertThat(bookingsByDate)
                .isNotEmpty()
                .hasSize(1)
                .contains(new Booking());
    }

    @Test
    @DisplayName("Поиск бронирований по имени бронировавшего")
    void getBookingsByUserName_whenInvoke_thenInvokeFindBookingByUserNameFromRepository() {
        String name = "name";
        when(workplaceBookingRepository.findBookingByUserName(name)).thenReturn(List.of(new Booking()));

        System.setIn(new ByteArrayInputStream(name.getBytes()));
        workplaceBookingService.getBookingsByUserName();

        verify(workplaceBookingRepository, times(1)).findBookingByUserName(name);
    }

    @Test
    @DisplayName("Поиск всех бронирований")
    void getAllBookings_whenInvoke_thenReturnListOfBookings() {
        when(workplaceBookingRepository.findAllBookings()).thenReturn(List.of(new Booking()));

        List<Booking> allBookings = workplaceBookingService.getAllBookings();

        verify(workplaceBookingRepository, times(1)).findAllBookings();
        assertThat(allBookings)
                .hasSize(1)
                .contains(new Booking());
    }
}