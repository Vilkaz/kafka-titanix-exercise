package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.model.Passenger;
import de.vwgis.kafkatitanixexercise.kafka.producer.CoreDataProducer;
import de.vwgis.kafkatitanixexercise.kafka.producer.GenericDataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.vwgis.kafkatitanixexercise.kafka.producer.PassengerProducer;

@RestController
@RequestMapping(value = "kafka")
public class KafkaRestController {


    private final PassengerProducer passengerProducer;
    private final GenericDataProducer genericDataProducer;
    private final CoreDataProducer coreDataProducer;

    private final Logger log = LoggerFactory.getLogger(KafkaRestController.class);

    @Autowired
    public KafkaRestController(PassengerProducer passengerProducer, CoreDataProducer coreDataProducer, GenericDataProducer genericDataProducer) {
        this.passengerProducer = passengerProducer;
        this.coreDataProducer = coreDataProducer;
        this.genericDataProducer = genericDataProducer;
    }

    @PostMapping("publish")
    public void sendOnePassengerToKafka(@RequestBody Passenger passenger) {
        log.info("received message from api: " + passenger);
        passengerProducer.publish(passenger);
    }

    @PostMapping("publishtest")
    public void sendOnePassengerToKafkaWithGenericDataProducer() {
        genericDataProducer.publish(null);
    }

    @PostMapping("init")
    public void sendCoreDataToKafka() {
        log.info("received core data initialisation request ");
        coreDataProducer.sendCoreDatasetToKafka();
    }


}
