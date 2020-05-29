package com.wiktorkielar.alansystemstask.repository.destination;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AlertDestinationRepositoryImpl implements AlertDestinationRepository {

    private static List<AlertEntity> alertEntities = new LinkedList<>();

    @Override
    public List<AlertEntity> getAlertEntities() {
        return alertEntities;
    }

    @Override
    public List<AlertEntity> createAlerts(List<AlertEntity> sourceAlertEntities) {

        alertEntities.addAll(sourceAlertEntities);

        return alertEntities;
    }
}
