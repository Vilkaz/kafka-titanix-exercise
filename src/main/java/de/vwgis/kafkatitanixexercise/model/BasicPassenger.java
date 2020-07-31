package de.vwgis.kafkatitanixexercise.model;

import java.math.BigDecimal;

public interface BasicPassenger {
    Integer getPassengerId();

    BigDecimal getFare();

    Boolean getSurvived();

    String getSex();

    Integer getPClass();

    String getName();

}