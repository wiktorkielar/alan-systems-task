package com.wiktorkielar.alansystemstask.repository.source;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertSourceRepositoryImpl implements AlertSourceRepository {

    //TO BE CHANGED
    public static List<AlertEntity> alertEntityList = new LinkedList<>();

    @Override
    public AlertEntity createAlert(AlertEntity alertEntity) {

        alertEntity.setId(alertEntityList.size() + 1);
        alertEntityList.add(alertEntity);

        return alertEntity;
    }

    @Override
    public List<AlertEntity> updateAlerts(List<AlertEntity> modifiedAlertEntityList) {

        for(AlertEntity alertEntity : alertEntityList){
            for(AlertEntity modifiedAlertEntity : modifiedAlertEntityList){
                if(alertEntity.getId() == modifiedAlertEntity.getId()){
                    alertEntity.setReplicated(modifiedAlertEntity.isReplicated());
                }
            }
        }

        return modifiedAlertEntityList;
    }


    @Override
    public List<AlertEntity> findNotReplicatedAlerts() {

        return alertEntityList.stream()
                .filter(e -> (e.isReplicated() == false))
                .collect(Collectors.toList());
    }


}
