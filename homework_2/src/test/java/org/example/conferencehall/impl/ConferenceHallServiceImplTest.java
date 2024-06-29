package org.example.conferencehall.impl;

import org.example.conferencehall.model.ConferenceHall;
import org.example.crud.CrudRepository;
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
class ConferenceHallServiceImplTest {

    private final ConferenceHallServiceImpl conferenceHallService = new ConferenceHallServiceImpl();

    @Mock
    private CrudRepository<ConferenceHall> conferenceHallRepository = new ConferenceHallRepositoryImpl();

    @BeforeEach
    void setUp() {
        conferenceHallService.setConferenceHallRepository(conferenceHallRepository);
    }

    @Test
    @DisplayName("")
    void createConferenceHall_whenInvokeWithValidConferenceHall_thenInvokeSaveMethod() {
        doNothing().when(conferenceHallRepository).save(any());

        System.setIn(new ByteArrayInputStream("Hall".getBytes()));
        conferenceHallService.createConferenceHall();

        verify(conferenceHallRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("")
    void deleteConferenceHall_whenInvokeWithValidConferenceHallId_thenInvokeDeleteMethod() {
        doNothing().when(conferenceHallRepository).delete(1);

        System.setIn(new ByteArrayInputStream("1".getBytes()));
        conferenceHallService.deleteConferenceHall();

        verify(conferenceHallRepository, times(1)).delete(1);
    }

    @Test
    @DisplayName("")
    void deleteConferenceHall_whenInvokeWithInValidConferenceHallId_thenDontInvokeDeleteMethod() {
        System.setIn(new ByteArrayInputStream("ert".getBytes()));

        verify(conferenceHallRepository, times(0)).delete(1);
    }

    @Test
    @DisplayName("")
    void findAllConferenceHalls_whenInvoke_thenReturnListOfConferenceHalls() {
        when(conferenceHallRepository.findAll()).thenReturn(List.of(new ConferenceHall(), new ConferenceHall()));

        List<ConferenceHall> conferenceHalls = conferenceHallService.findAllConferenceHalls();

        assertThat(conferenceHalls)
                .contains(new ConferenceHall())
                .hasSize(2);
    }
}