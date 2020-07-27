package de.vwgis.kafkatitanixexercise.kafka.producer;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.Passenger;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class PassengerProducer {

    private KafkaTemplate<String, Passenger> kafkaTemplate;

    @Autowired
    public PassengerProducer(KafkaTemplate<String, Passenger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Passenger passenger) {
        log.info("sending to kafka. Topic=" + TOPICS.PASSENGERS + ", passenger =" + passenger);
        kafkaTemplate.send(TOPICS.PASSENGERS, passenger);
    }

}
