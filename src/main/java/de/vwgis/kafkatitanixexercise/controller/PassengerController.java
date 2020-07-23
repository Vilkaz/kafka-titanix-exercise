package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.kafka.listener.PassengerSurvivorAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PassengerController {


    private final PassengerSurvivorAggregator aggregator;

    @Autowired
    public PassengerController(PassengerSurvivorAggregator service) {
        this.aggregator = service;
    }

    @GetMapping("firstClassInfo")
    public String getInformation() {
        return aggregator.getSurvivorInfo();
    }
}
