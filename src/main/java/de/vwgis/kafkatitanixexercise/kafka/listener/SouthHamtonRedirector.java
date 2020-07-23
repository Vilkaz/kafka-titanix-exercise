package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.Passenger;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class SouthHamtonRedirector {

    private final KafkaTemplate<String, Passenger> kafkaTemplate;

    @Autowired
    public SouthHamtonRedirector(KafkaTemplate<String, Passenger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = TOPICS.PASSENGERS, groupId = "SouthHamtonRedirector")
    public void redirect(GenericRecord record) {
        TitanicPassenger passenger = new TitanicPassenger(record);
        if ("S".equals(passenger.getEmbarked()) && passenger.getAge()>24) {
            kafkaTemplate.send(TOPICS.SOUTHHAMPTON, passenger.toPassenger());
        }
    }
}
