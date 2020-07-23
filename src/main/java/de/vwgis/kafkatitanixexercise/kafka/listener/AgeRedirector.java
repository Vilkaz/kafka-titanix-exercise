package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.Passenger;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
@Data
public class AgeRedirector {

    private final KafkaTemplate<String, Passenger> kafkaTemplate;

    @Autowired
    public AgeRedirector(KafkaTemplate<String, Passenger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = TOPICS.PASSENGERS, groupId = "missingAgeFinder")
    public void publishMissingAge(GenericRecord record) {
        TitanicPassenger passenger = new TitanicPassenger(record);
        if (passenger.getAge() == 0.0) {
            kafkaTemplate.send(TOPICS.MISSING_AGE, passenger.toPassenger());
            log.info("passenger " + passenger.getPassengerId() +  " with age = 0.0 was pusched into topic " + TOPICS.MISSING_AGE);
        }
    }
}
