package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.kafka.listener.IncomeAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class IncomeController {

    private final IncomeAggregator aggregator;

    @Autowired
    public IncomeController(IncomeAggregator aggregator) {
        this.aggregator = aggregator;
    }

    @GetMapping("income")
    public String getIncome() {
        return aggregator.getIncomeInfo();
    }
}
