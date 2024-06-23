package org.example.workplace.impl;

import org.example.crud.CrudRepository;
import org.example.workplace.model.Workplace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkPlaceServiceImplTest {

    private final WorkPlaceServiceImpl workPlaceService = new WorkPlaceServiceImpl();

    @Mock
    private CrudRepository<Workplace> workPlaceRepository = new WorkPlaceRepositoryImpl();

    @BeforeEach
    void setUp() {
        workPlaceService.setWorkPlaceRepository(workPlaceRepository);
    }

    @Test
    void createWorkplace() {
        Workplace workplace = new Workplace(1, 1);
        doNothing().when(workPlaceRepository).save(workplace);

        System.setIn(new ByteArrayInputStream("1".getBytes()));
        workPlaceService.createWorkplace();

        verify(workPlaceRepository, times(1)).save(workplace);
    }

    @Test
    void createWorkplace_whenInvokeWithStringInput_whenThrowsException() {
        Workplace workplace = new Workplace(1, 1);

        System.setIn(new ByteArrayInputStream("qwe".getBytes()));
        workPlaceService.createWorkplace();

        verify(workPlaceRepository, times(0)).save(workplace);
    }

    @Test
    void deleteWorkplace() {
        doNothing().when(workPlaceRepository).delete(1);

        System.setIn(new ByteArrayInputStream("1".getBytes()));
        workPlaceService.deleteWorkplace();

        verify(workPlaceRepository, times(1)).delete(1);
    }

    @Test
    void deleteWorkplace_whenInvokeWithStringInput_whenThrowsException() {
        System.setIn(new ByteArrayInputStream("ert".getBytes()));

        verify(workPlaceRepository, times(0)).delete(1);
    }

    @Test
    void findAllWorkplaces() {
        when(workPlaceRepository.findAll()).thenReturn(List.of(new Workplace(), new Workplace()));

        List<Workplace> workplaces = workPlaceService.findAllWorkplaces();

        assertThat(workplaces)
                .hasSize(2);
    }
}