package org.example.workplace.impl;

import org.example.crud.CrudRepository;
import org.example.workplace.model.Workplace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Создание рабочего места")
    void createWorkplace_whenInvokeWithValidWorkplace_thenCreateNewWorkplace() {
        doNothing().when(workPlaceRepository).save(any());

        System.setIn(new ByteArrayInputStream("1".getBytes()));
        workPlaceService.createWorkplace();

        verify(workPlaceRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Создание рабочего места с вводом текстом вместо номера этажа")
    void createWorkplace_whenInvokeWithStringInput_whenThrowsException() {
        Workplace workplace = new Workplace(1, 1);

        System.setIn(new ByteArrayInputStream("qwe".getBytes()));
        workPlaceService.createWorkplace();

        verify(workPlaceRepository, times(0)).save(workplace);
    }

    @Test
    @DisplayName("Удаление рабочего места")
    void deleteWorkplace_whenInvokeWithValidWorkplaceId_thenInvokeDeleteMethod() {
        doNothing().when(workPlaceRepository).delete(1);

        System.setIn(new ByteArrayInputStream("1".getBytes()));
        workPlaceService.deleteWorkplace();

        verify(workPlaceRepository, times(1)).delete(1);
    }

    @Test
    @DisplayName("Удаление рабочего места с вводом текста вместо номера этажа")
    void deleteWorkplace_whenInvokeWithStringInput_whenThrowsException() {
        System.setIn(new ByteArrayInputStream("ert".getBytes()));

        verify(workPlaceRepository, times(0)).delete(1);
    }

    @Test
    @DisplayName("Поиск всех рабочих мест")
    void findAllWorkplaces_whenInvoke_thenReturnListOfTwoWorkplaces() {
        when(workPlaceRepository.findAll()).thenReturn(List.of(new Workplace(), new Workplace()));

        List<Workplace> workplaces = workPlaceService.findAllWorkplaces();

        assertThat(workplaces)
                .hasSize(2);
    }
}