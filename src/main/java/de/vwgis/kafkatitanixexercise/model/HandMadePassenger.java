//package de.vwgis.kafkatitanixexercise.model;
//
//import com.opencsv.bean.CsvBindByName;
//import lombok.Data;
//import org.apache.avro.Schema;
//import org.apache.avro.specific.SpecificRecord;
//
//
//@Data
//public class HandMadePassenger extends SpecificRecord {
//
//    @CsvBindByName
//    private Integer passengerId;
//    @CsvBindByName
//    private Boolean survived;
//    @CsvBindByName
//    private Integer pClass;
//    @CsvBindByName
//    private String name;
//    @CsvBindByName()
//    private String sex;
//    @CsvBindByName()
//    private Double age;
//    @CsvBindByName()
//    private Integer sibSp;
//    @CsvBindByName()
//    private Integer parch;
//    @CsvBindByName()
//    private String ticket;
//    @CsvBindByName()
//    private String fare;
//    @CsvBindByName()
//    private String cabin;
//    @CsvBindByName()
//    private String embarked;
//
//    public static final org.apache.avro.Schema SCHEMA = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Passenger\",\"namespace\":\"de.vwgis.kafkatitanixexercise.model\",\"fields\":[{\"name\":\"passengerId\",\"type\":\"int\"},{\"name\":\"survived\",\"type\":\"boolean\"},{\"name\":\"pClass\",\"type\":\"int\",\"default\":3},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"sex\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"double\"},{\"name\":\"sibSp\",\"type\":\"int\"},{\"name\":\"parch\",\"type\":\"int\"},{\"name\":\"ticket\",\"type\":\"string\"},{\"name\":\"fare\",\"type\":\"double\",\"default\":0.0},{\"name\":\"cabin\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"embarked\",\"type\":\"string\"}]}");
//
//
//    @Override
//    public void put(int i, Object v) {
//        switch (i) {
//            case 0: passengerId = (java.lang.Integer)v; break;
//            case 1: survived = (java.lang.Boolean)v; break;
//            case 2: pClass = (java.lang.Integer)v; break;
//            case 3: name = (java.lang.String)v; break;
//            case 4: sex = (java.lang.String)v; break;
//            case 5: age = (java.lang.Double)v; break;
//            case 6: sibSp = (java.lang.Integer)v; break;
//            case 7: parch = (java.lang.Integer)v; break;
//            case 8: ticket = (java.lang.String)v; break;
//            case 9: fare = (java.lang.String)v; break;
//            case 10: cabin = (java.lang.String)v; break;
//            case 11: embarked = (java.lang.String)v; break;
//            default: throw new IndexOutOfBoundsException("Invalid index: " + i);
//        }
//    }
//
//    @Override
//    public Object get(int i) {
//        switch (i) {
//            case 0:
//                return passengerId;
//            case 1:
//                return survived;
//            case 2:
//                return pClass;
//            case 3:
//                return name;
//            case 4:
//                return sex;
//            case 5:
//                return age;
//            case 6:
//                return sibSp;
//            case 7:
//                return parch;
//            case 8:
//                return ticket;
//            case 9:
//                return fare;
//            case 10:
//                return cabin;
//            case 11:
//                return embarked;
//            default:
//                throw new IndexOutOfBoundsException("Invalid index: " + i);
//        }
//    }
//
//    @Override
//    public Schema getSchema() {
//        return SCHEMA;
//    }
//}
