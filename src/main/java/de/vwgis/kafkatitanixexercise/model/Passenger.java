package de.vwgis.kafkatitanixexercise.model;

import com.opencsv.bean.CsvBindByName;

public class Passenger {

    @CsvBindByName
    private String passengerId;
    @CsvBindByName
    private Boolean survived;
    @CsvBindByName
    private int pClass;
    @CsvBindByName
    private String name;
    @CsvBindByName()
    private String sex;
    @CsvBindByName()
    private double age;
    @CsvBindByName()
    private int sibSp;
    @CsvBindByName()
    private int parch;
    @CsvBindByName()
    private String ticket;
    @CsvBindByName()
    private String fare;
    @CsvBindByName()
    private String cabin;
    @CsvBindByName()
    private String embarked;
}
