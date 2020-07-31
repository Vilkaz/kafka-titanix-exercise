package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@CommonsLog
@Service
public class IncomeAggregator {


    /**
     * Those variables simulate the Database for this specific Test Task
     */
    private BigDecimal income;
    private BigDecimal survivorsIncome;

    public IncomeAggregator() {
        income = BigDecimal.ZERO;
        survivorsIncome =BigDecimal.ZERO;
    }

    @KafkaListener(topics = TOPICS.PASSENGERS, groupId = "incomeConsumers")
    public void incomeConsumer(GenericRecord record) {
        BasicPassenger passenger = new TitanicPassenger(record);
        addIncome(passenger);
    }

    private void addIncome(BasicPassenger passenger) {
        income = income.add(passenger.getFare());
        survivorsIncome = survivorsIncome.add(extractSurvivorsIncome(passenger));
        log.debug("added income from Passenger " + passenger.getPassengerId());
    }

    private BigDecimal extractSurvivorsIncome(BasicPassenger passenger) {
        return passenger.getSurvived() ? passenger.getFare() : BigDecimal.ZERO;
    }

    public String getIncomeInfo() {
        return "Income total : " + income + " only Survivors = " + survivorsIncome;
    }

}
