package org.example.booking.impl.workplace;

import org.example.booking.model.Booking;
import org.example.booking.model.PlaceType;
import org.example.booking.model.Slot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WorkplaceBookingRepositoryImplTest {

    private WorkplaceBookingRepositoryImpl workplaceBookingRepository;

    private Booking booking;

    @BeforeEach
    void setUp() {
        workplaceBookingRepository = new WorkplaceBookingRepositoryImpl();
        booking = Booking.builder()
                .bookingId(1)
                .slot(Slot.AFTERNOON_SLOT)
                .bookingDate(LocalDate.now().plusDays(2))
                .placeType(PlaceType.WORKPLACE)
                .bookerName("name")
                .placeId(1)
                .build();
    }

    @AfterEach
    void tearDown() {
        workplaceBookingRepository = null;
    }

    /*@Test
    @DisplayName("")
    void save_whenInvokeWithValidBooking_thenSaveBooking() {
        workplaceBookingRepository.save(booking);

        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.findWorkPlaceBookings();

        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .hasSize(1);
    }

    @Test
    @DisplayName("")
    void save_whenInvokeWithNull_thenSaveNothing() {
        workplaceBookingRepository.save(null);

        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();

        assertThat(workplaceBookings)
                .isEmpty();
    }

    @Test
    @DisplayName("")
    void deleteBooking_whenInvokeWithValidBookingIdAndUserName_thenDeleteBooking() {
        workplaceBookingRepository.save(booking);
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .hasSize(1);

        workplaceBookingRepository.deleteBooking(1, "name");

        assertThat(workplaceBookings)
                .isEmpty();
    }

    @Test
    @DisplayName("")
    void deleteBooking_whenInvokeWithInValidUserName_thenDeleteNothing() {
        workplaceBookingRepository.save(booking);
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .hasSize(1);

        workplaceBookingRepository.deleteBooking(1, "qwerty");

        assertThat(workplaceBookings)
                .hasSize(1);
    }

    @Test
    @DisplayName("")
    void findBookingByUserName_whenInvokeWithValidUserName_thenReturnListOfOnlyBooking() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(2))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .placeId(1)
                .build();
        workplaceBookingRepository.save(booking);
        workplaceBookingRepository.save(newBooking);
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);


        List<Booking> bookings = workplaceBookingRepository.findBookingByUserName("name");

        assertThat(bookings)
                .containsExactly(booking)
                .hasSize(1);
    }

    @Test
    @DisplayName("")
    void findBookingByUserName_whenInvokeWithInValidUserName_thenReturnEmptyList() {
        workplaceBookingRepository.save(booking);

        List<Booking> bookings = workplaceBookingRepository.findBookingByUserName("asdfgh");

        assertThat(bookings)
                .isEmpty();
    }

    @Test
    @DisplayName("")
    void findBookingByDate_whenInvokeWithValidDate_thenReturnCorrectBooking() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(3))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .placeId(1)
                .build();
        workplaceBookingRepository.save(booking);
        workplaceBookingRepository.save(newBooking);
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);

        List<Booking> bookings = workplaceBookingRepository.findBookingByDate(LocalDate.now().plusDays(2));

        assertThat(bookings)
                .containsExactly(booking)
                .hasSize(1);
    }

    @Test
    @DisplayName("")
    void findBookingByDate_whenInvokeWithInCorrectDate_thenReturnEmptyList() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(3))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .placeId(1)
                .build();
        workplaceBookingRepository.save(booking);
        workplaceBookingRepository.save(newBooking);
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);

        List<Booking> bookings = workplaceBookingRepository.findBookingByDate(LocalDate.now().plusDays(5));

        assertThat(bookings)
                .isEmpty();
    }

    @Test
    @DisplayName("")
    void findAllBookings_thenInvoke_thenReturnListOfTwoBookings() {
        Booking newBooking = Booking.builder()
                .bookingId(2)
                .slot(Slot.MORNING_SLOT)
                .bookingDate(LocalDate.now().plusDays(3))
                .placeType(PlaceType.CONFERENCE_HALL)
                .bookerName("Another name")
                .placeId(1)
                .build();
        workplaceBookingRepository.save(booking);
        workplaceBookingRepository.save(newBooking);
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .containsEntry(1, booking)
                .containsEntry(2, newBooking)
                .hasSize(2);

        List<Booking> bookings = workplaceBookingRepository.findAllBookings();

        assertThat(bookings)
                .contains(booking)
                .contains(newBooking)
                .hasSize(2);
    }

    @Test
    @DisplayName("")
    void findAllBookings_whenInvoke_thenReturnEmptyList() {
        Map<Integer, Booking> workplaceBookings = workplaceBookingRepository.getWorkPlaceBookings();
        assertThat(workplaceBookings)
                .isEmpty();

        List<Booking> bookings = workplaceBookingRepository.findAllBookings();

        assertThat(bookings)
                .isEmpty();
    }*/
}