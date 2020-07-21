package de.vwgis.kafkatitanixexercise.service;


import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import lombok.Data;

@Data
public class PassengersData {

    private int male;
    private int female;
    private int maleSurvivors;
    private int femaleSurvivors;


    void consumePassenger(BasicPassenger passenger) {
        switch (passenger.getSex()) {
            case ("male"):
                male++;
                maleSurvivors += passenger.getSurvived() ? 1 : 0;
                break;
            case ("female"):
                female++;
                femaleSurvivors += passenger.getSurvived() ? 1 : 0;
                break;
        }
    }

    PassengersData addStats(PassengersData otherData) {
        male +=otherData.getMale();
        female +=otherData.getFemale();
        maleSurvivors +=otherData.getMaleSurvivors();
        femaleSurvivors +=otherData.getFemaleSurvivors();
        return this;
    }

    String getStatus() {
        return String.format("Males: %d (%d survivors) Females %d (%d survivors)", male, maleSurvivors, female, femaleSurvivors);

    }


}


