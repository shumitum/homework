package org.example.conferencehall.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class ConferenceHall {
    private Integer conferenceHallId;
    private String name;
}
