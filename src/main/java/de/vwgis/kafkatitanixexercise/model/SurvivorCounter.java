package de.vwgis.kafkatitanixexercise.model;

import java.util.function.BiConsumer;

enum SurvivorCounter {
    male((basicPassenger, passengersData) -> passengersData.consumeMale(basicPassenger)),
    female((basicPassenger, passengersData) -> passengersData.consumeFemale(basicPassenger));

    private final BiConsumer<BasicPassenger, PassengersData> passengerDataCounter;

    SurvivorCounter(BiConsumer<BasicPassenger, PassengersData> passengerDataCounter) {
        this.passengerDataCounter = passengerDataCounter;
    }

    void count(BasicPassenger basicPassenger, PassengersData passengersData) {
        passengerDataCounter.accept(basicPassenger, passengersData);
    }
}