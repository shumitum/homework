package org.example.booking.impl.conferencehall;

import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.example.testcontainer.TestContainer;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConferenceHallBookingRepositoryImplTest {

    private ConferenceHallBookingRepositoryImpl conferenceHallBookingRepository;
    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;

    private Booking booking;
    private Booking anotherBooking;

    @BeforeAll
    static void setUpAll() {
        postgreSQLContainer = TestContainer.getPostgresContainer();

    }

    @BeforeEach
    void setUp() {
        conferenceHallBookingRepository = new ConferenceHallBookingRepositoryImpl();
        booking = Booking.builder()
                .bookingId(1)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.of(2024, 7, 10))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("qwe")
                .placeId(1)
                .build();
        anotherBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.AFTERNOON_SLOT)
                .bookingDate(LocalDate.of(2024, 7, 11))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("qwe")
                .placeId(1)
                .build();
    }


    @AfterEach
    void tearDown() {
        conferenceHallBookingRepository = null;
    }

    @Test
    @DisplayName("Поиск бронирований по имени пользователя")
    void findBookingByUserName_whenInvokeWithValidUserName_thenReturnListOfOnlyBooking() {
        List<Booking> bookings = conferenceHallBookingRepository.findBookingByUserName("qwe");
        assertThat(bookings)
                .contains(booking)
                .contains(anotherBooking)
                .hasSize(2);
    }

    @Test
    @DisplayName("Поиск бронирований по имени пользователя по несуществующему имени")
    void findBookingByUserName_whenInvokeWithInValidUserName_thenReturnEmptyList() {
        List<Booking> bookings = conferenceHallBookingRepository.findBookingByUserName("asdfgh");

        assertThat(bookings)
                .isEmpty();
    }

    @Test
    @DisplayName("Поиск бронирований по дате")
    void findBookingByDate_whenInvokeWithValidDate_thenReturnCorrectBooking() {
        LocalDate date = LocalDate.of(2024, 7, 10);
        List<Booking> bookings = conferenceHallBookingRepository.findBookingByDate(date);

        assertThat(bookings)
                .containsExactly(booking)
                .hasSize(1);
    }

    @Test
    @DisplayName("Поиск бронирований по дате на которую ничего не бронировали")
    void findBookingByDate_whenInvokeWithInCorrectDate_thenReturnEmptyList() {
        LocalDate date = LocalDate.of(2024, 7, 19);
        List<Booking> bookings = conferenceHallBookingRepository.findBookingByDate(date);

        assertThat(bookings)
                .isEmpty();
    }

    @Test
    @DisplayName("Поиск всех бронирований")
    void findAllBookings_thenInvoke_thenReturnListOfTwoBookings() {
        List<Booking> bookings = conferenceHallBookingRepository.findAllBookings();

        assertThat(bookings)
                .contains(booking)
                .contains(anotherBooking)
                .hasSize(2);
    }
}