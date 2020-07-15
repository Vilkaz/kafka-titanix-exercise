package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class PassengerProducer {

    @Value("${topic.name}")
    private String TOPIC;
    private KafkaTemplate<String, Passenger> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(PassengerProducer.class);

    @Autowired
    public PassengerProducer(KafkaTemplate<String, Passenger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Passenger passenger) {
        logger.info("sending to kafka. Topic=" + TOPIC + ", passenger =" + passenger);
        ListenableFuture<SendResult<String, Passenger>> send = kafkaTemplate.send(TOPIC, passenger);
        kafkaTemplate.flush();
        System.out.println();
    }

}
