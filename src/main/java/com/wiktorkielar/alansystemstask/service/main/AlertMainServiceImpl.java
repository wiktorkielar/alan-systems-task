package com.wiktorkielar.alansystemstask.service.main;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import com.wiktorkielar.alansystemstask.repository.source.AlertSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertMainServiceImpl implements AlertMainService {

    private AlertSourceRepository alertSourceRepository;

    @Autowired
    public AlertMainServiceImpl(AlertSourceRepository alertSourceRepository) {
        this.alertSourceRepository = alertSourceRepository;
    }

    @Override
    public AlertEntity create(AlertEntity alertEntity) {
        return alertSourceRepository.createAlert(alertEntity);
    }
}
