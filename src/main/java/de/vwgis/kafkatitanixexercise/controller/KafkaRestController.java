package de.vwgis.kafkatitanixexercise.controller;

import de.vwgis.kafkatitanixexercise.service.CoreDataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

//    @PostMapping("/publish")
//    public void sendMessageToKafka(@RequestParam("message") String message) {
//        log.info("received message from api: " + message );
//        passengerProducer.sendMessage(message);
//    }

    @PostMapping("/init")
    public void sendCoreDataToKafka() {
        log.info("received core data initialisation request ");
        coreDataProducer.sendCoreDatasetToKafka();
    }


}
