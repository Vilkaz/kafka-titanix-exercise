package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.BasicPassenger;
import de.vwgis.kafkatitanixexercise.model.NamesForClass;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@CommonsLog
@Service
public class NameAggregator {

    /**
     * Database replacement for this exercise.
     *
     * First index is Class, second contains names of that class.
     */
    private Map<Integer, NamesForClass> namesForSpecificClasses;
    private final NamesForClass allClasses;

    @Autowired
    public NameAggregator(NamesForClass allClasses) {
        this.allClasses = allClasses;
        allClasses.setPClass(0);
        namesForSpecificClasses = new HashMap<>();
    }


    @KafkaListener(topics = TOPICS.PASSENGERS, groupId = "nameConsumer")
    public void aggregateNames(GenericRecord record) {
        BasicPassenger passenger = new TitanicPassenger(record);
        Integer pClass = passenger.getPClass();

        addPassengerForTotalList(passenger);
        addPassengerForSpecificClass(passenger, pClass);

        log.debug("aggregated name for passenger " + passenger.getPassengerId());
        }

    private void addPassengerForTotalList(BasicPassenger passenger) {
        allClasses.add(passenger);
    }

    private void addPassengerForSpecificClass(BasicPassenger passenger, Integer pClass) {
        NamesForClass namesForClass = Optional
                .ofNullable(namesForSpecificClasses.get(pClass))
                .orElse(new NamesForClass(pClass));
        namesForClass.add(passenger);
        //important for initial filling
        namesForSpecificClasses.put(pClass, namesForClass);
    }


    public String getNamesForAllClasses(int limiter) {
        String topNamesFromAllClasses = String.format("All Classes: %s", allClasses.getTopNames(limiter));

        String resultForSpesificClass =  namesForSpecificClasses.values()
                .stream()
                .map(npc -> String.format("Class %d %s", npc.getPClass(), npc.getTopNames(limiter)))
                .collect(Collectors.joining("\n"));

        return topNamesFromAllClasses + System.lineSeparator() +  resultForSpesificClass;
        }





}
