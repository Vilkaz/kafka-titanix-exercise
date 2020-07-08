package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.Topiclist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PassengerConsumer {

    private final Logger logger = LoggerFactory.getLogger(PassengerConsumer.class);

    @KafkaListener(topics = Topiclist.PASSENGERS, groupId = "passengerConsumers")
    public void consume(String message) {
        logger.info("consumed message from kafka: " + message);
    }

}
