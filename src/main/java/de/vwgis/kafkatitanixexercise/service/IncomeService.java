package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import de.vwgis.kafkatitanixexercise.model.CSVInputPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class IncomeService {


    /**
     * Those static variabels simulate the Database for this specific Test Task
     */
    private static double income;
    private static double survivorsIncome;

    @KafkaListener(topics = "passenger", groupId = "incomeConsumers")
    public void incomeConsumer(GenericRecord record) {
        CSVInputPassenger passenger = new CSVInputPassenger(record);
        addIncome(passenger);
        log.info("consumed message from kafka: " + record);
    }

    private void addIncome(BasicPassenger passenger) {
        income += passenger.getFare();
        survivorsIncome += passenger.getSurvived() ? passenger.getFare() : 0;
        log.info("added income from Passenger " + passenger.getPassengerId());
    }

    public String getIncomeInfo() {
        return "Income total : " + income + " only Survivors = " + survivorsIncome;
    }

}
