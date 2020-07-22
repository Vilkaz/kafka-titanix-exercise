package de.vwgis.kafkatitanixexercise.model;


import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * A Class for to acumulate Data about all Passengers.
 *
 * Kinda like replacement for a DB in this exercise.
 */
@Component
@Data
public class PassengersData {

    private int male;
    private int female;
    private int maleSurvivors;
    private int femaleSurvivors;


    public void consumePassenger(BasicPassenger passenger) {
        SurvivorCounter survivorCounter = SurvivorCounter.valueOf(passenger.getSex());
        survivorCounter.count(passenger, this);
    }

    void consumeMale(BasicPassenger passenger) {
        male++;
        maleSurvivors += passenger.getSurvived() ? 1 : 0;
    }

    void consumeFemale(BasicPassenger passenger) {
        female++;
        femaleSurvivors += passenger.getSurvived() ? 1 : 0;
    }

    public String getStatus() {
        return String.format("Males: %d (%d survivors) Females %d (%d survivors)", male, maleSurvivors, female, femaleSurvivors);
    }

}


