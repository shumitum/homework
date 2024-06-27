package org.example.user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    /*@Test
    void saveUser_whenInvoke_thenSaveUserCredentials() {
        Map<String, String> registeredUsers = userRepository.getRegisteredUser();
        assertThat(registeredUsers)
                .isEmpty();

        userRepository.saveUser("name", "password");

        assertThat(registeredUsers)
                .isNotEmpty()
                .containsEntry("name", "password");
    }

    @Test
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

    //@Test
    //void checkUserCredentials_whenInvokeWithExistsCredentials_thenReturnTrue() {
    //    userRepository.saveUser("name", "password");
    //    Map<String, String> registeredUsers = userRepository.getRegisteredUser();
    //    assertThat(registeredUsers)
    //            .isNotEmpty()
    //            .hasSize(1)
    //            .containsEntry("name", "password");
//
    //    boolean isUserExists = userRepository.checkUserCredentials("name", "password");
    //    assertThat(isUserExists)
    //            .isTrue();
    //}
//
    //@Test
    //void checkUserCredentials_whenInvokeWithNonExistsCredentials_thenReturnFalse() {
    //    userRepository.saveUser("name", "password");
    //    Map<String, String> registeredUsers = userRepository.getRegisteredUser();
    //    assertThat(registeredUsers)
    //            .isNotEmpty()
    //            .hasSize(1)
    //            .containsEntry("name", "password");
//
    //    boolean isUserExists = userRepository.checkUserCredentials("qwe", "asd");
    //    assertThat(isUserExists)
    //            .isFalse();
    //}
}