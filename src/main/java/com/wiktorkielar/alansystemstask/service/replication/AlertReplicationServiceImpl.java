package com.wiktorkielar.alansystemstask.service.replication;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import com.wiktorkielar.alansystemstask.repository.destination.AlertDestinationRepository;
import com.wiktorkielar.alansystemstask.repository.source.AlertSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertReplicationServiceImpl implements AlertReplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertReplicationServiceImpl.class);
    private AlertSourceRepository alertSourceRepository;
    private AlertDestinationRepository alertDestinationRepository;

    @Autowired
    public AlertReplicationServiceImpl(AlertSourceRepository alertSourceRepository, AlertDestinationRepository alertDestinationRepository) {
        this.alertSourceRepository = alertSourceRepository;
        this.alertDestinationRepository = alertDestinationRepository;
    }

    @Override
    @Scheduled(fixedDelay = 5000)
    public void replicateAlerts() {

        showDiagnostics("DB State before replication: ");

        List<AlertEntity> alertEntities = alertSourceRepository.findNotReplicatedAlerts().stream()
                .peek(e -> e.setReplicated(true))
                .collect(Collectors.toList());

        alertSourceRepository.updateAlerts(alertEntities);
        alertDestinationRepository.createAlerts(alertEntities);

        alertEntities.forEach(e -> LOGGER.info("New replication: {}", e));

        showDiagnostics("DB State after replication: ");
    }

    private void showDiagnostics(String header) {
        LOGGER.debug(header);
        LOGGER.debug("SOURCE:      {}", Arrays.toString(alertSourceRepository.getAlertEntities().toArray()));
        LOGGER.debug("DESTINATION: {}", Arrays.toString(alertDestinationRepository.getAlertEntities().toArray()));
    }
}
