package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/income")
public class IncomeController {

    private final IncomeService service;

    @Autowired
    public IncomeController(IncomeService service) {
        this.service = service;
    }

    @GetMapping
    public String getIncome() {
        return service.getIncomeInfo();
    }
}
