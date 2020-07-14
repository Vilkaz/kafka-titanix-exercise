package de.vwgis.kafkatitanixexercise.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CSVInputPassenger {

    @CsvBindByName
    private Integer passengerId;
    @CsvBindByName
    private Boolean survived;
    @CsvBindByName
    private Integer pClass;
    @CsvBindByName
    private String name;
    @CsvBindByName()
    private String sex;
    @CsvBindByName()
    private double age;
    @CsvBindByName()
    private Integer sibSp;
    @CsvBindByName()
    private Integer parch;
    @CsvBindByName()
    private String ticket;
    @CsvBindByName()
    private Double fare;
    @CsvBindByName()
    private String cabin;
    @CsvBindByName()
    private String embarked;


    public Passenger toPassenger() {
        return Passenger.newBuilder()
                .setPassengerId(passengerId)
                .setSurvived(survived)
                .setPClass(pClass)
                .setName(name)
                .setSex(sex)
                .setAge(age)
                .setSibSp(sibSp)
                .setParch(parch)
                .setTicket(ticket)
                .setFare(fare)
                .setCabin(cabin)
                .setEmbarked(embarked)
                .build();
    }


}
