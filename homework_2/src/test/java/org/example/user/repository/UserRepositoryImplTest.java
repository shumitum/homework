package org.example.user.repository;

import org.example.testcontainer.TestContainer;
import org.example.user.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class UserRepositoryImplTest {

    private static UserRepositoryImpl userRepository;

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;

    @BeforeAll
    static void setUpAll() {
        postgreSQLContainer = TestContainer.getPostgresContainer();
        userRepository = new UserRepositoryImpl();
    }

/*    @Test
    @DisplayName("")
    void saveUser_whenInvoke_thenSaveUserCredentials() {
        Map<String, String> registeredUsers = userRepository.getRegisteredUser();
        assertThat(registeredUsers)
                .isEmpty();

        userRepository.saveUser("name", "password");

        assertThat(registeredUsers)
                .isNotEmpty()
                .containsEntry("name", "password");
    }*/

    /*@Test
    @DisplayName("")
    void saveUser_whenInvokeWithAlreadyExistUser_thenNotSaveUserCredentials() {
        userRepository.saveUser("name", "password");
        Map<String, String> registeredUsers = userRepository.getRegisteredUser();
        assertThat(registeredUsers)
                .isNotEmpty()
                .hasSize(1)
                .containsEntry("name", "password");

        userRepository.saveUser("name", "password");

        assertThat(registeredUsers)
                .isNotEmpty()
                .hasSize(1)
                .containsEntry("name", "password");
    }*/

    @Test
    @DisplayName("")
    void checkUserCredentials_whenInvokeWithExistsCredentials_thenReturnUser() {
        Optional<User> userByCredentials = userRepository.findUserByCredentials("qwe", "qwe");

        assertThat(userByCredentials)
                .isPresent()
                .contains(new User(1, "qwe", "qwe"));
    }

    @Test
    @DisplayName("")
    void checkUserCredentials_whenInvokeWithNonExistsCredentials_thenReturnFalse() {
        Optional<User> userByCredentials = userRepository.findUserByCredentials("xcv", "xcv");

        assertThat(userByCredentials)
                .isEmpty();
    }
}