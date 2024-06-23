package org.example.booking.impl.conferencehall;

import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ConferenceHallBookingRepositoryImplTest {

    private ConferenceHallBookingRepositoryImpl conferenceHallBookingRepository;

    private Booking booking;

    @BeforeEach
    void setUp() {
        conferenceHallBookingRepository = new ConferenceHallBookingRepositoryImpl();
        booking = Booking.builder()
                .bookingId(1)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(2))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("name")
                .bookingPlaceId(1)
                .build();
    }

    @AfterEach
    void tearDown() {
        conferenceHallBookingRepository = null;
    }

    @Test
    void save_whenInvokeWithValidBooking_thenSaveBooking() {
        conferenceHallBookingRepository.save(booking);

        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();

        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .hasSize(1);
    }

    @Test
    void save_whenInvokeWithNull_thenSaveNothing() {
        conferenceHallBookingRepository.save(null);

        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();

        assertThat(conferenceHallBookings)
                .isEmpty();
    }

    @Test
    void deleteBooking_whenInvokeWithValidBookingIdAndUserName_thenDeleteBooking() {
        conferenceHallBookingRepository.save(booking);
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .hasSize(1);

        conferenceHallBookingRepository.deleteBooking(1, "name");

        assertThat(conferenceHallBookings)
                .isEmpty();
    }

    @Test
    void deleteBooking_whenInvokeWithInValidUserName_thenDeleteNothing() {
        conferenceHallBookingRepository.save(booking);
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .hasSize(1);

        conferenceHallBookingRepository.deleteBooking(1, "qwerty");

        assertThat(conferenceHallBookings)
                .hasSize(1);
    }

    @Test
    void findBookingByUserName_whenInvokeWithValidUserName_thenReturnListOfOnlyBooking() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(2))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .bookingPlaceId(1)
                .build();
        conferenceHallBookingRepository.save(booking);
        conferenceHallBookingRepository.save(newBooking);
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);


        List<Booking> bookings = conferenceHallBookingRepository.findBookingByUserName("name");

        assertThat(bookings)
                .containsExactly(booking)
                .hasSize(1);
    }

    @Test
    void findBookingByUserName_whenInvokeWithInValidUserName_thenReturnEmptyList() {
        conferenceHallBookingRepository.save(booking);

        List<Booking> bookings = conferenceHallBookingRepository.findBookingByUserName("asdfgh");

        assertThat(bookings)
                .isEmpty();
    }

    @Test
    void findBookingByDate_whenInvokeWithValidDate_thenReturnCorrectBooking() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(3))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .bookingPlaceId(1)
                .build();
        conferenceHallBookingRepository.save(booking);
        conferenceHallBookingRepository.save(newBooking);
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);

        List<Booking> bookings = conferenceHallBookingRepository.findBookingByDate(LocalDate.now().plusDays(2));

        assertThat(bookings)
                .containsExactly(booking)
                .hasSize(1);
    }

    @Test
    void findBookingByDate_whenInvokeWithInCorrectDate_thenReturnEmptyList() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(3))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .bookingPlaceId(1)
                .build();
        conferenceHallBookingRepository.save(booking);
        conferenceHallBookingRepository.save(newBooking);
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);

        List<Booking> bookings = conferenceHallBookingRepository.findBookingByDate(LocalDate.now().plusDays(5));

        assertThat(bookings)
                .isEmpty();
    }

    @Test
    void findAllBookings_thenInvoke_thenReturnListOfTwoBookings() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(3))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .bookingPlaceId(1)
                .build();
        conferenceHallBookingRepository.save(booking);
        conferenceHallBookingRepository.save(newBooking);
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);

        List<Booking> bookings = conferenceHallBookingRepository.findAllBookings();

        assertThat(bookings)
                .contains(booking)
                .contains(newBooking)
                .hasSize(2);
    }

    @Test
    void findAllBookings_whenInvoke_thenReturnEmptyList() {
        Map<Integer, Booking> conferenceHallBookings = conferenceHallBookingRepository.getConferenceHallBookings();
        assertThat(conferenceHallBookings)
                .isEmpty();

        List<Booking> bookings = conferenceHallBookingRepository.findAllBookings();

        assertThat(bookings)
                .isEmpty();
    }
}