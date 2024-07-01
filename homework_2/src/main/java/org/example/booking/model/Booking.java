package org.example.booking.model;

import lombok.*;

import java.time.LocalDate;

/**
 * Бронирование
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    /**
     * ID бронирования
     */
    private Integer bookingId;

    /**
     * Дата бронирования
     */
    private LocalDate bookingDate;

    /**
     * Слот бронирования
     */
    private Slot slot;

    /**
     * Тип места для бронирования
     */
    private PlaceType placeType;

    /**
     * Имя бронирующего
     */
    private String bookerName;

    /**
     * ID бронируемого места
     */
    private Integer placeId;
}
