package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class PassengerController {


    private final PassengerService service;

    @Autowired
    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping("firstClassInfo")
    public String getInformation() {
        return service.getFirstClassInfo();

    }
}
