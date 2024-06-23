package org.example.workplace.impl;

import org.example.workplace.model.Workplace;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WorkPlaceRepositoryImplTest {

    private WorkPlaceRepositoryImpl workPlaceRepository;

    private Workplace workplace;

    @BeforeEach
    void setUp() {
        workPlaceRepository = new WorkPlaceRepositoryImpl();
        workplace = new Workplace(1, 2);
    }

    @AfterEach
    void tearDown() {
        workPlaceRepository = null;
    }

    @Test
    void save_whenInvokeWithValidWorkplace_thenSaveWorkplace() {
        workPlaceRepository.save(workplace);

        List<Workplace> all = workPlaceRepository.findAll();

        assertThat(all.get(0))
                .isEqualTo(workplace);
    }

    @Test
    void save_whenInvokeWithNull_thenDontSaveWorkplace() {
        workPlaceRepository.save(null);

        List<Workplace> all = workPlaceRepository.findAll();

        assertThat(all.size())
                .isZero();
    }

    @Test
    void update_whenInvokeWithValidWorkPlaceId_thenUpdateWorkplace() {
        workPlaceRepository.save(workplace);
        Workplace updatedWorkplace = new Workplace(1, 4);

        workPlaceRepository.update(updatedWorkplace);

        List<Workplace> all = workPlaceRepository.findAll();
        assertThat(all.get(0))
                .isEqualTo(updatedWorkplace);
    }

    @Test
    void update_whenInvokeWithNonExistsWorkplace_thenThrowsException() {
        workPlaceRepository.save(workplace);
        Workplace updatedWorkplace = new Workplace(2, 4);

        assertThatThrownBy(() -> workPlaceRepository.update(updatedWorkplace)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete_whenInvokeWithValidWorkPlaceId_thenDeleteWorkplace() {
        workPlaceRepository.save(workplace);

        workPlaceRepository.delete(1);

        List<Workplace> all = workPlaceRepository.findAll();

        assertThat(all.size())
                .isZero();
    }

    @Test
    void delete_whenInvokeWithInvalidWorkPlaceId_thenThrowsException() {
        workPlaceRepository.save(workplace);

        assertThatThrownBy(() -> workPlaceRepository.delete(2)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findAll() {
        workPlaceRepository.save(workplace);
        workPlaceRepository.save(new Workplace(2, 2));

        List<Workplace> all = workPlaceRepository.findAll();

        assertThat(all)
                .hasSize(2);
    }

    @Test
    void existsById_whenInvokeWithValidWorkPlaceId_thenReturnTrue() {
        workPlaceRepository.save(workplace);

        boolean isWorkplaceExists = workPlaceRepository.existsById(1);

        assertThat(isWorkplaceExists)
                .isTrue();
    }

    @Test
    void existsById_whenInvokeWithInvalidWorkPlaceId_thenReturnFalse() {
        workPlaceRepository.save(workplace);

        boolean isWorkplaceExists = workPlaceRepository.existsById(2);

        assertThat(isWorkplaceExists)
                .isFalse();
    }
}