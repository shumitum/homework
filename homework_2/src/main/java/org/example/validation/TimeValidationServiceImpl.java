package org.example.validation;

import org.example.exception.TimeValidationException;

import java.time.LocalDate;

public class TimeValidationServiceImpl implements TimeValidationService {
    @Override
    public void checkBookingIsNotBeforeNow(LocalDate bookingDate) {
        if (bookingDate != null && bookingDate.isBefore(LocalDate.now().plusDays(1))) {
            throw new TimeValidationException("дата бронирования должна быть в будущем");
        }
    }
}
