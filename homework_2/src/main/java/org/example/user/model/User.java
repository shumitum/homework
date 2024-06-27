package org.example.user.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Integer id;
    private String userName;
    @Getter(AccessLevel.NONE)
    private String password;
}
