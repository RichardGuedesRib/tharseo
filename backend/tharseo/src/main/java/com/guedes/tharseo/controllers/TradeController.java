package com.guedes.tharseo.controllers;

import com.guedes.tharseo.external.TradeMethodsFactory;
import com.guedes.tharseo.services.TradeService;
import com.guedes.tharseo.services.UserAssetService;
import com.guedes.tharseo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trades")
public class TradeController {

    @Autowired
    TradeService tradeService;
    @Autowired
    UserService userService;
    @Autowired
    UserAssetService userAssetService;

    @Autowired
    TradeMethodsFactory tradeMethodsFactory;



}
