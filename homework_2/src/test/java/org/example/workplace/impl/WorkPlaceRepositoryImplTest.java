package org.example.workplace.impl;

import org.example.testcontainer.TestContainer;
import org.example.workplace.model.Workplace;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorkPlaceRepositoryImplTest {

    private WorkPlaceRepositoryImpl workPlaceRepository = new WorkPlaceRepositoryImpl();
    private Workplace workplace;
    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;

    @BeforeAll
    static void setUpAll() {
        postgreSQLContainer = TestContainer.getPostgresContainer();
    }

    @BeforeEach
    void setUp() {
        workplace = new Workplace(1, 1);
    }

    @Test
    @Order(1)
    @DisplayName("Найти все рабочие места")
    void findAll_whenInvoke_thenReturnList() {
        List<Workplace> all = workPlaceRepository.findAll();

        assertThat(all)
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @Order(2)
    @DisplayName("Проверка наличия рабочего места в БД по ID")
    void existsById_whenInvokeWithValidWorkPlaceId_thenReturnTrue() {
        boolean isWorkplaceExists = workPlaceRepository.existsById(1);

        assertThat(isWorkplaceExists)
                .isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("Проверка наличия рабочего места в БД по несуществующему ID")
    void existsById_whenInvokeWithInvalidWorkPlaceId_thenReturnFalse() {
        boolean isWorkplaceExists = workPlaceRepository.existsById(5);

        assertThat(isWorkplaceExists)
                .isFalse();
    }

    @Test
    @Order(4)
    @DisplayName("Найти все рабочие места - список из двух")
    void findAll_whenInvoke_thenReturnListWithTwoElements() {
        Workplace newWorkplace = Workplace.builder()
                .floor(5)
                .build();
        workPlaceRepository.save(newWorkplace);
        List<Workplace> all = workPlaceRepository.findAll();

        assertThat(all)
                .isNotEmpty()
                .hasSize(2);
    }
}