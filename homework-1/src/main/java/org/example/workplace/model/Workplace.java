package org.example.workplace.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.place.Place;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Workplace extends Place {
    //private Integer workplaceId;
    private Integer floor;

    public Workplace(Integer placeId, Integer floor) {
        super(placeId);
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "workplaceId=" + getPlaceId() +
                ", floor=" + floor +
                "} ";
    }
}