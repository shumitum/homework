package org.example.workplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Рабочее место
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workplace {
    /**
     * ID рабочего места
     */
    private Integer workplaceId;

    /**
     * Этаж на котором находится рабочее место
     */
    private Integer floor;
}