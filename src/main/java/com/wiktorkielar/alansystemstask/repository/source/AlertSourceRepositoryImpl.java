package com.wiktorkielar.alansystemstask.repository.source;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertSourceRepositoryImpl implements AlertSourceRepository {

    private static List<AlertEntity> alertEntities = new LinkedList<>();

    @Override
    public List<AlertEntity> getAlertEntities() {
        return alertEntities;
    }

    @Override
    public AlertEntity createAlert(AlertEntity alertEntity) {

        alertEntity.setId(alertEntities.size() + 1);
        alertEntities.add(alertEntity);

        return alertEntity;
    }

    @Override
    public List<AlertEntity> updateAlerts(List<AlertEntity> modifiedAlertEntities) {

        for (AlertEntity alertEntity : alertEntities) {
            for (AlertEntity modifiedAlertEntity : modifiedAlertEntities) {
                if (alertEntity.getId() == modifiedAlertEntity.getId()) {
                    alertEntity.setReplicated(modifiedAlertEntity.isReplicated());
                }
            }
        }

        return modifiedAlertEntities;
    }


    @Override
    public List<AlertEntity> findNotReplicatedAlerts() {

        return alertEntities.stream()
                .filter(e -> !e.isReplicated())
                .collect(Collectors.toList());
    }


}
