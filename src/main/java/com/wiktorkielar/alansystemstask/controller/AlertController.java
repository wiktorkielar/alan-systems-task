package com.wiktorkielar.alansystemstask.controller;

import com.wiktorkielar.alansystemstask.model.AlertDto;
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
@RequestMapping(value = "${alertcontroller.requestmapping.value}")
public class AlertController {

    @Autowired
    private AlertMainService alertMainService;

    @PostMapping
    public ResponseEntity<AlertRequest> createAlert(@RequestBody AlertRequest alertRequest) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AlertDto alertDto = modelMapper.map(alertRequest, AlertDto.class);
        alertMainService.create(alertDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
