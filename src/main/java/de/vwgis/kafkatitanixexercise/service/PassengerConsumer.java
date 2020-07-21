package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.CSVInputPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@CommonsLog(topic = "PassengerConsumer Logger")
@Service
public class PassengerConsumer {

    @Value("${topic.name}")
    private String TOPIC;

    private final IncomeService incomeService;

    public PassengerConsumer(IncomeService incomeService) {
        this.incomeService = incomeService;
    }


    @KafkaListener(topics = "passenger", groupId = "passengerConsumers")
    public void consume(GenericRecord record) {
        CSVInputPassenger passenger = new CSVInputPassenger(record);
        incomeService.addIncome(passenger);
        log.info("consumed message from kafka: " + record);
    }

}
