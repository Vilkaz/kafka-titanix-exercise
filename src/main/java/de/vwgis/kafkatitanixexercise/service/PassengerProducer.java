package de.vwgis.kafkatitanixexercise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PassengerProducer {

    public static final String TOPIC = "passengers";
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(PassengerProducer.class);

    @Autowired
    public PassengerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        logger.info("sending to kafka. Topic=" + TOPIC + ", message=" + message);
        kafkaTemplate.send(TOPIC, message);
    }

    public static String getTOPIC() {
        return TOPIC;
    }
}
