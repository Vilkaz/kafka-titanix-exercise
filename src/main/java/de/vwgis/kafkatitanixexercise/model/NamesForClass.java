package de.vwgis.kafkatitanixexercise.model;

import de.vwgis.kafkatitanixexercise.common.LastNameExtractor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@NoArgsConstructor
@Component
@CommonsLog
@Data
public class NamesForClass {

    private Integer pClass;

    public NamesForClass(Integer pClass) {
        this.pClass = pClass;
    }

    private Map<String, NameCounter> names = new HashMap<>();

    public void add(BasicPassenger passenger) {
        String name = LastNameExtractor.extract(passenger);
        NameCounter nameCounter = Optional
                .ofNullable(names.get(name))
                .orElse(new NameCounter(name));
        nameCounter.increment();
        names.put(name, nameCounter);
    }

    public String getTopNames(int limiter) {
        String information = names.values()
                .stream()
                .sorted()
                .limit(limiter)
                .map(nameCounter -> String.format("%s : %d times", nameCounter.getName(), nameCounter.getAmount()))
                .collect(Collectors.joining(","));
        return String.format("Top %d names are : %s",  limiter, information);
    }

}
