package org.example.booking.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Integer bookingId;
    private LocalDate bookingDate;
    private Slot slot;
    private PlaceType placeType;
    private String bookerName;
    private Integer bookingPlaceId;
}
