package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class IncomeAggregator {


    /**
     * Those variables simulate the Database for this specific Test Task
     */
    private double income;
    private double survivorsIncome;

    @KafkaListener(topics = TOPICS.PASSENGERS, groupId = "incomeConsumers")
    public void incomeConsumer(GenericRecord record) {
        BasicPassenger passenger = new TitanicPassenger(record);
        addIncome(passenger);
    }

    private void addIncome(BasicPassenger passenger) {
        income += passenger.getFare();
        survivorsIncome += passenger.getSurvived() ? passenger.getFare() : 0;
        log.debug("added income from Passenger " + passenger.getPassengerId());
    }

    public String getIncomeInfo() {
        return "Income total : " + income + " only Survivors = " + survivorsIncome;
    }

}
