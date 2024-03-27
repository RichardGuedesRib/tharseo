package com.fatec.dsm.tharseo.controllers;

import com.fatec.dsm.tharseo.models.Kline;
import com.fatec.dsm.tharseo.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/chart")
@CrossOrigin(origins = "http://localhost:3000")
public class ChartController {

    @Autowired
    ChartService chartService;

    @GetMapping
    ResponseEntity<?> getChartBtc1m(){
        List<Kline> klines = chartService.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(klines);
    }
}