package com.wiktorkielar.alansystemstask.service.main;

import com.wiktorkielar.alansystemstask.model.AlertDto;
import com.wiktorkielar.alansystemstask.model.AlertEntity;
import com.wiktorkielar.alansystemstask.repository.source.AlertSourceRepository;
import com.wiktorkielar.alansystemstask.service.scheduling.AlertReplicationServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertMainServiceImpl implements AlertMainService {

    private static final Logger logger = LoggerFactory.getLogger(AlertMainServiceImpl.class);

    @Autowired
    private AlertSourceRepository alertSourceRepository;

    @Override
    public AlertDto create(AlertDto requestAlertDto) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AlertEntity requestAlertEntity = modelMapper.map(requestAlertDto, AlertEntity.class);
        AlertEntity responseAlertEntity = alertSourceRepository.createAlert(requestAlertEntity);

        AlertDto responseAlertDto = modelMapper.map(responseAlertEntity, AlertDto.class);

        return responseAlertDto;
    }
}
