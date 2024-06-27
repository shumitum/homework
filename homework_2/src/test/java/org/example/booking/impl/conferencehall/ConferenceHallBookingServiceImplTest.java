package org.example.booking.impl.conferencehall;

import org.example.booking.BookingRepository;
import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.conferencehall.impl.ConferenceHallRepositoryImpl;
import org.example.conferencehall.model.ConferenceHall;
import org.example.crud.CrudRepository;
import org.example.user.model.User;
import org.example.user.service.AuthenticationService;
import org.example.user.service.impl.AuthenticationServiceImpl;
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
class ConferenceHallBookingServiceImplTest {

    private final ConferenceHallBookingServiceImpl conferenceHallBookingService = new ConferenceHallBookingServiceImpl();

    @Mock
    private CrudRepository<ConferenceHall> conferenceHallRepository = new ConferenceHallRepositoryImpl();

    @Mock
    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @Mock
    private BookingRepository conferenceHallBookingRepository = new ConferenceHallBookingRepositoryImpl();

    private Booking booking;

    @BeforeEach
    void setUp() {
        conferenceHallBookingService.setAuthenticationService(authenticationService);
        conferenceHallBookingService.setConferenceHallRepository(conferenceHallRepository);
        conferenceHallBookingService.setConferenceHallBookingRepository(conferenceHallBookingRepository);
        booking = Booking.builder()
                .bookingId(1)
                .slot(Slot.AFTERNOON_SLOT)
                .bookingDate(LocalDate.of(2024, 7, 25))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("name")
                .placeId(1)
                .build();
    }

    //@Test
    //void cancelBooking_whenInvoke_thenInvokeMethodsGetAuthorizedUserAndDeleteBooking() {
    //    when(authenticationService.getAuthorizedUser()).thenReturn(new User("name", "123"));
    //    doNothing().when(conferenceHallBookingRepository).deleteBooking(1, "name");
//
    //    System.setIn(new ByteArrayInputStream("1".getBytes()));
    //    conferenceHallBookingService.cancelBooking();
//
    //    verify(authenticationService, times(1)).getAuthorizedUser();
    //    verify(conferenceHallBookingRepository, times(1)).deleteBooking(1, "name");
    //}

    @Test
    void getAvailableSlotsByDate_whenInvoke_thenInvokeMethodsFindBookingByDateAndFindAll() {
        LocalDate date = LocalDate.of(2024, 7, 25);
        when(conferenceHallBookingRepository.findBookingByDate(date)).thenReturn(List.of(booking));
        when(conferenceHallRepository.findAll()).thenReturn(List.of(new ConferenceHall()));


        System.setIn(new ByteArrayInputStream("2024-07-25".getBytes()));
        conferenceHallBookingService.getAvailableSlotsByDate();

        verify(conferenceHallBookingRepository, times(1)).findBookingByDate(date);
        verify(conferenceHallRepository, times(1)).findAll();
    }

    @Test
    void getBookingsByDate_whenInvokeWithValidDate_thenInvokeFindBookingByDateMethodFromRepository() {
        LocalDate date = LocalDate.of(2024, 7, 25);
        when(conferenceHallBookingRepository.findBookingByDate(date)).thenReturn(List.of(new Booking()));

        System.setIn(new ByteArrayInputStream("2024-07-25".getBytes()));
        conferenceHallBookingService.getBookingsByDate();

        verify(conferenceHallBookingRepository, times(1)).findBookingByDate(date);
    }

    @Test
    void getBookingsByUserName_whenInvoke_thenInvokeFindBookingByUserNameFromRepository() {
        String name = "name";
        when(conferenceHallBookingRepository.findBookingByUserName(name)).thenReturn(List.of(new Booking()));

        System.setIn(new ByteArrayInputStream(name.getBytes()));
        conferenceHallBookingService.getBookingsByUserName();

        verify(conferenceHallBookingRepository, times(1)).findBookingByUserName(name);
    }

    @Test
    void getAllBookings_whenInvoke_thenReturnListOfBookings() {
        when(conferenceHallBookingRepository.findAllBookings()).thenReturn(List.of(new Booking()));

        List<Booking> allBookings = conferenceHallBookingService.getAllBookings();

        verify(conferenceHallBookingRepository, times(1)).findAllBookings();
        assertThat(allBookings)
                .hasSize(1);
    }
}