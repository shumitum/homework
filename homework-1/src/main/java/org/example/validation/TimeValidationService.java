package org.example.validation;

import java.time.LocalDate;

public interface TimeValidationService {
    void checkBookingIsNotBeforeNow(LocalDate bookingDate);
}
