package org.example.conferencehall.model;

import lombok.*;

/**
 * Конференц зал
 */
@Getter
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ConferenceHall {
    /**
     *ID конференц зала
     */
    private Integer conferenceHallId;

    /**
     * Название конференц-зала
     */
    private String name;
}
