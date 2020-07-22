package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import de.vwgis.kafkatitanixexercise.model.PassengersData;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@CommonsLog(topic = "PassengerConsumer Logger")
@Service
public class PassengerSurvivorAggregator {

    public static final int PASSENGER_CLASS = 1;
    /**
     * This simulates a Database for the test example;
     */
    private final PassengersData firstClassPassengersData;

    @Autowired
    public PassengerSurvivorAggregator(PassengersData passengersData) {
        this.firstClassPassengersData = passengersData;
    }

    @KafkaListener(topics = "passenger", groupId = "passengerConsumers")
    public void consumeFirstClassPassenger(GenericRecord record) {
        TitanicPassenger passenger = new TitanicPassenger(record);
        if (passenger.getPClass() == PASSENGER_CLASS) {
            firstClassPassengersData.consumePassenger(passenger);
        }
        log.info("consumed passenger from kafka: " + passenger);
    }

    public String getFirstClassInfo() {
        return firstClassPassengersData.getStatus();
    }
}
