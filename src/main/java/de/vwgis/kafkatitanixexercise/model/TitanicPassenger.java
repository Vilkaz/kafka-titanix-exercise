package de.vwgis.kafkatitanixexercise.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.avro.generic.GenericRecord;

@NoArgsConstructor
@Data
public class TitanicPassenger implements BasicPassenger{

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


    public TitanicPassenger(GenericRecord record) {
        passengerId = extractIntFromObject(record.get("passengerId"));
        survived = extractBooleanFromObject(record.get("survived"));
        fare = extractDoubleFromObject(record.get("fare"));
        sex = String.valueOf(record.get("sex"));
        pClass = extractIntFromObject(record.get("pClass"));
        name = String.valueOf(record.get("name"));
    }

    private Double extractDoubleFromObject(Object data) {
        return Double.valueOf(String.valueOf(data));
    }


    private Boolean extractBooleanFromObject(Object data) {
        return Boolean.valueOf(String.valueOf(data));
    }

    private Integer extractIntFromObject(Object data) {
        return Integer.valueOf(String.valueOf(data));
    }

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
