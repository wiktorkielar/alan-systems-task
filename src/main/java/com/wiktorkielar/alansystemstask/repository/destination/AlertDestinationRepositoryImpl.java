package com.wiktorkielar.alansystemstask.repository.destination;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AlertDestinationRepositoryImpl implements AlertDestinationRepository {

    //TO BE CHANGED
    public static List<AlertEntity> alertEntityList = new LinkedList<>();

    @Override
    public List<AlertEntity> createAlerts(List<AlertEntity> sourceAlertEntityList) {

       alertEntityList.addAll(sourceAlertEntityList);

       return alertEntityList;
    }
}
