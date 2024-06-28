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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

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

    //@Test
    //void cancelBooking_whenInvoke_thenInvokeMethodsGetAuthorizedUserAndDeleteBooking() {
    //    when(authenticationService.getAuthorizedUser()).thenReturn(new User("name", "123"));
    //    doNothing().when(workplaceBookingRepository).deleteBooking(1, "name");

    //    System.setIn(new ByteArrayInputStream("1".getBytes()));
    //    workplaceBookingService.cancelBooking();

    //    verify(authenticationService, times(1)).getAuthorizedUser();
    //    verify(workplaceBookingRepository, times(1)).deleteBooking(1, "name");
    //}

    //@Test
    //void getAvailableSlotsByDate_whenInvoke_thenInvokeMethodsFindBookingByDateAndFindAll() {
    //    LocalDate date = LocalDate.of(2024, 7, 25);
    //    when(workplaceBookingRepository.findBookingByDate(date)).thenReturn(List.of(booking));
    //    when(workPlaceRepository.findAll()).thenReturn(List.of(new Workplace(1, 1)));
//
//
    //    System.setIn(new ByteArrayInputStream("2024-07-25".getBytes()));
    //    workplaceBookingService.getAvailableSlotsByDate();
//
    //    verify(workplaceBookingRepository, times(1)).findBookingByDate(date);
    //    verify(workPlaceRepository, times(1)).findAll();
    //}

    @Test
    void getBookingsByDate_whenInvokeWithValidDate_thenInvokeFindBookingByDateMethodFromRepository() {
        LocalDate date = LocalDate.of(2024, 7, 25);
        when(workplaceBookingRepository.findBookingByDate(date)).thenReturn(List.of(new Booking()));

        System.setIn(new ByteArrayInputStream("2024-07-25".getBytes()));
        workplaceBookingService.getBookingsByDate();

        verify(workplaceBookingRepository, times(1)).findBookingByDate(date);
    }

    @Test
    void getBookingsByUserName_whenInvoke_thenInvokeFindBookingByUserNameFromRepository() {
        String name = "name";
        when(workplaceBookingRepository.findBookingByUserName(name)).thenReturn(List.of(new Booking()));

        System.setIn(new ByteArrayInputStream(name.getBytes()));
        workplaceBookingService.getBookingsByUserName();

        verify(workplaceBookingRepository, times(1)).findBookingByUserName(name);
    }

    @Test
    void getAllBookings_whenInvoke_thenReturnListOfBookings() {
        when(workplaceBookingRepository.findAllBookings()).thenReturn(List.of(new Booking()));

        List<Booking> allBookings = workplaceBookingService.getAllBookings();

        verify(workplaceBookingRepository, times(1)).findAllBookings();
        assertThat(allBookings)
                .hasSize(1);
    }
}