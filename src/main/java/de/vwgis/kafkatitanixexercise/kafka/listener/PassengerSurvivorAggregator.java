package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import de.vwgis.kafkatitanixexercise.model.PassengersData;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@CommonsLog(topic = "PassengerConsumer Logger")
@Service
public class PassengerSurvivorAggregator {

    public static final int PASSENGER_CLASS = 1;
    /**
     * This simulates a Database for the test example;
     */
    private final Map<Integer, PassengersData> passengers = new TreeMap<>();

    @KafkaListener(topics = TOPICS.PASSENGERS, groupId = "passengerConsumers")
    public void consumeFirstClassPassenger(GenericRecord record) {
        TitanicPassenger passenger = new TitanicPassenger(record);
        Integer pClass = passenger.getPClass();
        PassengersData passengersData = Optional.ofNullable(passengers.get(pClass))
                .orElse(new PassengersData());
        passengersData.consumePassenger(passenger);
        passengers.put(pClass, passengersData);
        log.debug("consumed passenger from kafka: " + passenger);
    }

    public String getSurvivorInfo() {
        return passengers.entrySet()
                .stream()
                .map(this::createStatusMessage)
                .collect(Collectors.joining("\n"));
    }

    private String createStatusMessage(Map.Entry<Integer, PassengersData> entry) {
        return String.format("Class : %d %s", entry.getKey(), entry.getValue().getStatus());
    }
}
