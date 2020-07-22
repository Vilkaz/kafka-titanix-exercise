package de.vwgis.kafkatitanixexercise.controller;


import de.vwgis.kafkatitanixexercise.kafka.listener.NameAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class NameController {

    private final NameAggregator aggregator;

    @Autowired
    public NameController(NameAggregator aggregator) {
        this.aggregator = aggregator;
    }

    @GetMapping("topNamesPerClass")
    public String topNamesPerClass(@RequestBody() Integer limiter) {
        return aggregator.getNamesForAllClasses(limiter);
    }


}
