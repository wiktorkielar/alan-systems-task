package com.wiktorkielar.alansystemstask.repository.source;

import com.wiktorkielar.alansystemstask.model.AlertEntity;

import java.util.List;

public interface AlertSourceRepository {

    AlertEntity createAlert(AlertEntity AlertEntity);

    List<AlertEntity> updateAlerts(List<AlertEntity> alertEntities);

    List<AlertEntity> findNotReplicatedAlerts();

    List<AlertEntity> getAlertEntities();
}