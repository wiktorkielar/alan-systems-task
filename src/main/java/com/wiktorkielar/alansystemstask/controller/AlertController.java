package com.wiktorkielar.alansystemstask.controller;

import com.wiktorkielar.alansystemstask.model.AlertEntity;
import com.wiktorkielar.alansystemstask.model.AlertRequest;
import com.wiktorkielar.alansystemstask.service.main.AlertMainService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${alertcontroller.requestmapping.value}")
public class AlertController {

    private AlertMainService alertMainService;

    @Autowired
    public AlertController(AlertMainService alertMainService) {
        this.alertMainService = alertMainService;
    }

    @PostMapping
    public ResponseEntity createAlert(@RequestBody AlertRequest alertRequest) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AlertEntity alertEntity = modelMapper.map(alertRequest, AlertEntity.class);
        alertMainService.create(alertEntity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
