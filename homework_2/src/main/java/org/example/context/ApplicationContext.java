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
import org.example.in.UserInput;
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

public class ApplicationContext {

    private static ApplicationContext applicationContext;
    private UserRepository userRepository;
    private RegistrationService registrationService;
    private AuthenticationService authenticationService;
    private Menu conferenceHallMenu;
    private Menu workplaceMenu;
    private Menu bookingMenu;
    private CrudRepository<ConferenceHall> conferenceHallRepository;
    private ConferenceHallService conferenceHallService;
    private CrudRepository<Workplace> workPlaceRepository;
    private WorkPlaceService workPlaceService;
    private BookingService workplaceBookingService;
    private BookingService conferenceHallBookingService;
    private BookingRepository workplaceBookingRepository;
    private BookingRepository conferenceHallBookingRepository;
    private TimeValidationService timeValidationService;
    private UserInput userInput;
    private WorkplaceBookingController workplaceBookingController;
    private ConferenceHallBookingController conferenceHallBookingController;

    private ApplicationContext() {
    }

    public ConferenceHallBookingController getConferenceHallBookingController() {
        if (conferenceHallBookingController == null) {
            conferenceHallBookingController = new ConferenceHallBookingController();
        }
        return conferenceHallBookingController;
    }

    public WorkplaceBookingController getWorkplaceBookingController() {
        if (workplaceBookingController == null) {
            workplaceBookingController = new WorkplaceBookingController();
        }
        return workplaceBookingController;
    }

    public UserInput getUserInput() {
        if (userInput == null) {
            userInput = new UserInput();
        }
        return userInput;
    }

    public TimeValidationService getTimeValidationService() {
        if (timeValidationService == null) {
            timeValidationService = new TimeValidationServiceImpl();
        }
        return timeValidationService;
    }

    public BookingRepository getConferenceHallBookingRepository() {
        if (conferenceHallBookingRepository == null) {
            conferenceHallBookingRepository = new ConferenceHallBookingRepositoryImpl();
        }
        return conferenceHallBookingRepository;
    }

    public BookingRepository getWorkplaceBookingRepository() {
        if (workplaceBookingRepository == null) {
            workplaceBookingRepository = new WorkplaceBookingRepositoryImpl();
        }
        return workplaceBookingRepository;
    }

    public BookingService getConferenceHallBookingService() {
        if (conferenceHallBookingService == null) {
            conferenceHallBookingService = new ConferenceHallBookingServiceImpl();
        }
        return conferenceHallBookingService;
    }

    public BookingService getWorkplaceBookingService() {
        if (workplaceBookingService == null) {
            workplaceBookingService = new WorkplaceBookingServiceImpl();
        }
        return workplaceBookingService;
    }

    public WorkPlaceService getWorkPlaceService() {
        if (workPlaceService == null) {
            workPlaceService = new WorkPlaceServiceImpl();
        }
        return workPlaceService;
    }

    public CrudRepository<Workplace> getWorkPlaceRepository() {
        if (workPlaceRepository == null) {
            workPlaceRepository = new WorkPlaceRepositoryImpl();
        }
        return workPlaceRepository;
    }

    public ConferenceHallService getConferenceHallService() {
        if (conferenceHallService == null) {
            conferenceHallService = new ConferenceHallServiceImpl();
        }
        return conferenceHallService;
    }

    public CrudRepository<ConferenceHall> getConferenceHallRepository() {
        if (conferenceHallRepository == null) {
            conferenceHallRepository = new ConferenceHallRepositoryImpl();
        }
        return conferenceHallRepository;
    }

    public UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    public RegistrationService getRegistrationService() {
        if (registrationService == null) {
            registrationService = new RegistrationServiceImpl();
        }
        return registrationService;
    }

    public AuthenticationService getAuthenticationService() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationServiceImpl();
        }
        return authenticationService;
    }

    public Menu getConferenceHallMenu() {
        if (conferenceHallMenu == null) {
            conferenceHallMenu = new ConferenceHallMenu();
        }
        return conferenceHallMenu;
    }

    public Menu getWorkplaceMenu() {
        if (workplaceMenu == null) {
            workplaceMenu = new WorkplaceMenu();
        }
        return workplaceMenu;
    }

    public Menu getBookingMenu() {
        if (bookingMenu == null) {
            bookingMenu = new BookingMenu();
        }
        return bookingMenu;
    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }
}