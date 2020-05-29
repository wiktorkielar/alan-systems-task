package com.wiktorkielar.alansystemstask.repository.destination;

import com.wiktorkielar.alansystemstask.model.AlertEntity;

import java.util.List;

public interface AlertDestinationRepository {
    List<AlertEntity> createAlerts(List<AlertEntity> alertEntities);

    List<AlertEntity> getAlertEntities();
}
