package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.model.Passenger;
import de.vwgis.kafkatitanixexercise.service.CoreDataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.vwgis.kafkatitanixexercise.service.PassengerProducer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaRestController {


    private final PassengerProducer passengerProducer;
    private final CoreDataProducer coreDataProducer;

    private final Logger log = LoggerFactory.getLogger(KafkaRestController.class);

    @Autowired
    public KafkaRestController(PassengerProducer passengerProducer, CoreDataProducer coreDataProducer) {
        this.passengerProducer = passengerProducer;
        this.coreDataProducer = coreDataProducer;
    }

    @PostMapping("/publish")
    public void sendMessageToKafka(@RequestBody Passenger passenger) {
        log.info("received message from api: " + passenger );
        passengerProducer.publish(passenger);
    }

    @PostMapping("/init")
    public void sendCoreDataToKafka() {
        log.info("received core data initialisation request ");
        coreDataProducer.sendCoreDatasetToKafka();
    }


}
