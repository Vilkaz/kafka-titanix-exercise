package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import de.vwgis.kafkatitanixexercise.model.CSVInputPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CommonsLog(topic = "PassengerConsumer Logger")
@Service
public class PassengerService {

    @Value("${topic.name}")
    private String TOPIC;

    private int male;
    private int female;
    private int maleSurvivors;
    private int femaleSurvivors;

    /**
     * This simulates a Database for the test example;
     */
    private static final Collection<BasicPassenger> passengers = new ArrayList<>();

    @KafkaListener(topics = "passenger", groupId = "passengerConsumers")
    public void consumePassenger(GenericRecord record) {
        CSVInputPassenger passenger = new CSVInputPassenger(record);
        passengers.add(passenger);
        log.info("consumed passenger from kafka: " + passenger);
    }

    public String getFirstClassInfo() {
        PassengersData passengerInformation = passengers.stream()
                .collect(Collector.of(
                        PassengersData::new,
                        (passengersData, passenger) -> passengersData.consumePassenger(passenger),
                        (passengersData, passengersData2) -> passengersData.addStats(passengersData2)));

        return passengerInformation.getStatus();

    }
}
