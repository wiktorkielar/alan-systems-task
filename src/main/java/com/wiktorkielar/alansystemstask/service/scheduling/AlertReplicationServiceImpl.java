package com.wiktorkielar.alansystemstask.service.scheduling;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import com.wiktorkielar.alansystemstask.repository.destination.AlertDestinationRepository;
import com.wiktorkielar.alansystemstask.repository.destination.AlertDestinationRepositoryImpl;
import com.wiktorkielar.alansystemstask.repository.source.AlertSourceRepository;
import com.wiktorkielar.alansystemstask.repository.source.AlertSourceRepositoryImpl;
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

    private static final Logger logger = LoggerFactory.getLogger(AlertReplicationServiceImpl.class);
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

        List<AlertEntity> notReplicatedAlertEntityList = alertSourceRepository.findNotReplicatedAlerts();

        List<AlertEntity> toBeReplicatedAlertEntityList = notReplicatedAlertEntityList.stream()
                .map(e -> {
                    e.setReplicated(true);
                    return e;
                })
                .collect(Collectors.toList());

        alertSourceRepository.updateAlerts(toBeReplicatedAlertEntityList);
        alertDestinationRepository.createAlerts(toBeReplicatedAlertEntityList);

        toBeReplicatedAlertEntityList.stream()
                .forEach((e) -> logger.info("New replication: " + e));
    }

    void showDiagnostics(){
        System.out.println("SOURCE:      " + Arrays.toString(AlertSourceRepositoryImpl.alertEntityList.toArray()));
        System.out.println("DESTINATION: " + Arrays.toString(AlertDestinationRepositoryImpl.alertEntityList.toArray()));
    }
}
