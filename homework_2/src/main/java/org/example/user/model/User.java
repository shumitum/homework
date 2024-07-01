package org.example.user.model;

import lombok.*;

/**
 * Пользователь приложения
 */
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    /**
     * ID пользователя
     */
    private Integer id;

    /**
     * Имя пользователя
     */
    private String userName;

    /**
     * Пароль для доступа к приложению
     */
    @Getter(AccessLevel.NONE)
    private String password;
}
