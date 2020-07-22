package de.vwgis.kafkatitanixexercise.model;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * For better sorting of Names by namecouter, we created here a class with own comparator
 */
@Data
public class NameCounter implements Comparable<NameCounter>{

    private String name;
    private Integer amount;

    public NameCounter(String name) {
        this.name = name;
        amount = 0;
    }

    void increment() {
        amount++;
    }

    /**
     * The comparator has to give bigger Value first.
     * That's why i switched the places
     */
    @Override
    public int compareTo(NameCounter o) {
        return o.getAmount().compareTo(amount);
    }


}
