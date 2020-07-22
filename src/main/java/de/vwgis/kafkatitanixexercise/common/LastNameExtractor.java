package de.vwgis.kafkatitanixexercise.common;

import de.vwgis.kafkatitanixexercise.model.BasicPassenger;

public class LastNameExtractor {


    public static String extract(BasicPassenger passenger) {
        return passenger.getName().split(",")[0];
    }

}
