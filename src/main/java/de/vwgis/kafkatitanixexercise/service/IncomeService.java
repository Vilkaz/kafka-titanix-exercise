package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class IncomeService {


    /**
     * Those static variabels simulate the Database for this specific Test Task
     */
    private static double income;
    private static double survivorsIncome;


    /**
     * @param passenger
     */
    public void addIncome(BasicPassenger passenger) {
        income += passenger.getFare();
        survivorsIncome += passenger.getSurvived() ? passenger.getFare() : 0;
        log.info("added income from Passenger " + passenger.getPassengerId());
    }

    public String getIncomeInfo() {
        return "Income total : " + income + " only Survivors = " + survivorsIncome;
    }

}
