package com.wiktorkielar.alansystemstask.service.replication;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import com.wiktorkielar.alansystemstask.repository.destination.AlertDestinationRepository;
import com.wiktorkielar.alansystemstask.repository.source.AlertSourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlertReplicationServiceImplTest {

    @Mock
    private AlertSourceRepository alertSourceRepository;

    @Mock
    private AlertDestinationRepository alertDestinationRepository;

    private AlertReplicationService alertReplicationService;

    @BeforeEach
    public void setup() {
        alertReplicationService = new AlertReplicationServiceImpl(alertSourceRepository, alertDestinationRepository);
    }

    @Test
    void shouldReplicateAlerts() {

        List<AlertEntity> alertEntities = List.of(
                new AlertEntity(1, false, "msg1", 1),
                new AlertEntity(2, false, "msg2", 2),
                new AlertEntity(3, false, "msg3", 3)
        );

        List<AlertEntity> expectedAlertEntities = List.of(
                new AlertEntity(1, true, "msg1", 1),
                new AlertEntity(2, true, "msg2", 2),
                new AlertEntity(3, true, "msg3", 3)
        );

        when(alertSourceRepository.findNotReplicatedAlerts()).thenReturn(alertEntities);
        alertReplicationService.replicateAlerts();

        verify(alertSourceRepository).updateAlerts(expectedAlertEntities);
        verify(alertDestinationRepository).createAlerts(expectedAlertEntities);
    }


}