package org.example.context;

import org.example.booking.BookingRepository;
import org.example.booking.BookingService;
import org.example.booking.controller.ConferenceHallBookingController;
import org.example.booking.controller.WorkplaceBookingController;
import org.example.booking.impl.conferencehall.ConferenceHallBookingRepositoryImpl;
import org.example.booking.impl.conferencehall.ConferenceHallBookingServiceImpl;
import org.example.booking.impl.workplace.WorkplaceBookingRepositoryImpl;
import org.example.booking.impl.workplace.WorkplaceBookingServiceImpl;
import org.example.conferencehall.ConferenceHallService;
import org.example.conferencehall.impl.ConferenceHallRepositoryImpl;
import org.example.conferencehall.impl.ConferenceHallServiceImpl;
import org.example.conferencehall.model.ConferenceHall;
import org.example.crud.CrudRepository;
import org.example.menu.BookingMenu;
import org.example.menu.ConferenceHallMenu;
import org.example.menu.Menu;
import org.example.menu.WorkplaceMenu;
import org.example.user.repository.UserRepository;
import org.example.user.repository.UserRepositoryImpl;
import org.example.user.service.AuthenticationService;
import org.example.user.service.RegistrationService;
import org.example.user.service.impl.AuthenticationServiceImpl;
import org.example.user.service.impl.RegistrationServiceImpl;
import org.example.validation.TimeValidationService;
import org.example.validation.TimeValidationServiceImpl;
import org.example.workplace.WorkPlaceService;
import org.example.workplace.impl.WorkPlaceRepositoryImpl;
import org.example.workplace.impl.WorkPlaceServiceImpl;
import org.example.workplace.model.Workplace;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationContextTest {

    private final ApplicationContext context = ApplicationContext.getInstance();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getConferenceHallBookingController() {
        ConferenceHallBookingController conferenceHallBookingController = context.getConferenceHallBookingController();

        assertThat(conferenceHallBookingController)
                .isNotNull()
                .isInstanceOf(ConferenceHallBookingController.class);
    }

    @Test
    void getWorkplaceBookingController() {
        WorkplaceBookingController workplaceBookingController = context.getWorkplaceBookingController();

        assertThat(workplaceBookingController)
                .isNotNull()
                .isInstanceOf(WorkplaceBookingController.class);
    }

    @Test
    void getTimeValidationService() {
        TimeValidationService timeValidationService = context.getTimeValidationService();

        assertThat(timeValidationService)
                .isNotNull()
                .isInstanceOf(TimeValidationServiceImpl.class);
    }

    @Test
    void getConferenceHallBookingRepository() {
        BookingRepository conferenceHallBookingRepository = context.getConferenceHallBookingRepository();

        assertThat(conferenceHallBookingRepository)
                .isNotNull()
                .isInstanceOf(ConferenceHallBookingRepositoryImpl.class);
    }

    @Test
    void getWorkplaceBookingRepository() {
        BookingRepository workplaceBookingRepository = context.getWorkplaceBookingRepository();

        assertThat(workplaceBookingRepository)
                .isNotNull()
                .isInstanceOf(WorkplaceBookingRepositoryImpl.class);
    }

    @Test
    void getConferenceHallBookingService() {
        BookingService conferenceHallBookingService = context.getConferenceHallBookingService();

        assertThat(conferenceHallBookingService)
                .isNotNull()
                .isInstanceOf(ConferenceHallBookingServiceImpl.class);
    }

    @Test
    void getWorkplaceBookingService() {
        BookingService workplaceBookingService = context.getWorkplaceBookingService();

        assertThat(workplaceBookingService)
                .isNotNull()
                .isInstanceOf(WorkplaceBookingServiceImpl.class);
    }

    @Test
    void getWorkPlaceService() {
        WorkPlaceService workPlaceService = context.getWorkPlaceService();

        assertThat(workPlaceService)
                .isNotNull()
                .isInstanceOf(WorkPlaceServiceImpl.class);
    }

    @Test
    void getWorkPlaceRepository() {
        CrudRepository<Workplace> workplaceRepository = context.getWorkPlaceRepository();

        assertThat(workplaceRepository)
                .isNotNull()
                .isInstanceOf(WorkPlaceRepositoryImpl.class);
    }

    @Test
    void getConferenceHallService() {
        ConferenceHallService conferenceHallService = context.getConferenceHallService();


        assertThat(conferenceHallService)
                .isNotNull()
                .isInstanceOf(ConferenceHallServiceImpl.class);
    }

    @Test
    void getConferenceHallRepository() {
        CrudRepository<ConferenceHall> conferenceHallRepository = context.getConferenceHallRepository();

        assertThat(conferenceHallRepository)
                .isNotNull()
                .isInstanceOf(ConferenceHallRepositoryImpl.class);
    }

    @Test
    void getUserRepository() {
        UserRepository userRepository = context.getUserRepository();

        assertThat(userRepository)
                .isNotNull()
                .isInstanceOf(UserRepositoryImpl.class);
    }

    @Test
    void getRegistrationService() {
        RegistrationService registrationService = context.getRegistrationService();

        assertThat(registrationService)
                .isNotNull()
                .isInstanceOf(RegistrationServiceImpl.class);
    }

    @Test
    void getAuthenticationService() {
        AuthenticationService authenticationService = context.getAuthenticationService();

        assertThat(authenticationService)
                .isNotNull()
                .isInstanceOf(AuthenticationServiceImpl.class);
    }

    @Test
    void getConferenceHallMenu() {
        Menu conferenceHallMenu = context.getConferenceHallMenu();

        assertThat(conferenceHallMenu)
                .isNotNull()
                .isInstanceOf(ConferenceHallMenu.class);
    }

    @Test
    void getWorkplaceMenu() {
        Menu workplaceMenu = context.getWorkplaceMenu();

        assertThat(workplaceMenu)
                .isNotNull()
                .isInstanceOf(WorkplaceMenu.class);
    }

    @Test
    void getBookingMenu() {
        Menu bookingMenu = context.getBookingMenu();

        assertThat(bookingMenu)
                .isNotNull()
                .isInstanceOf(BookingMenu.class);
    }
}