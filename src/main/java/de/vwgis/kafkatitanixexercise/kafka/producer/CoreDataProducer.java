package de.vwgis.kafkatitanixexercise.kafka.producer;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.vwgis.kafkatitanixexercise.config.TOPICS;
import de.vwgis.kafkatitanixexercise.model.TitanicPassenger;
import de.vwgis.kafkatitanixexercise.model.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CoreDataProducer {

    private static final String CORE_DATASET_FILE = "static/train.csv";

    private final Logger log = LoggerFactory.getLogger(CoreDataProducer.class);
    private final KafkaTemplate<String, Passenger> kafkaTemplate;

    public CoreDataProducer(KafkaTemplate<String, Passenger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCoreDatasetToKafka() {
        log.info("Sending Core Dataset To kafka");

        CsvToBean csv = new CsvToBeanBuilder(createCSVReader())
                .withType(TitanicPassenger.class)
                .build();

        List<TitanicPassenger> csvPassengers = csv.parse();
        csvPassengers.forEach(p -> sendMessage(p.toPassenger()));
    }

    void sendMessage(Passenger passenger) {
        this.kafkaTemplate.send(TOPICS.PASSENGERS, String.valueOf(passenger.getPassengerId()), passenger);
        log.info(String.format("Send passenger to kafka  -> %s", passenger));
    }


    private CSVReader createCSVReader() {
        CSVReader csvReader = null;
        try {
            Resource resource = new ClassPathResource(CORE_DATASET_FILE);
            File file = resource.getFile();
            csvReader = new CSVReader(new FileReader(file));
            log.info("CSV FileReader was created successfully");
        } catch (IOException e) {
            log.error("Error while accessing the core Dataset, " + e.getMessage(), e);
        }
        return csvReader;
    }
}
