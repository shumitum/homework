package org.example.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Place {
    private Integer placeId;

    public Place(Integer placeId) {
        this.placeId = placeId;
    }

}
