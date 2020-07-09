package de.vwgis.kafkatitanixexercise.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.vwgis.kafkatitanixexercise.model.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CoreDataProducer {

    private static final String CORE_DATASET_FILE = "static/train.csv";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(CoreDataProducer.class);


    @Autowired
    public CoreDataProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCoreDatasetToKafka() {
        log.info("Sending Core Dataset To kafka");
        CsvToBean csv = new CsvToBeanBuilder(createCSVReader())
                .withType(Passenger.class)
                .build();
        System.out.println();
    }


    private CSVReader createCSVReader() {
        CSVReader csvReader = null;
        try {
            Resource resource = new ClassPathResource(CORE_DATASET_FILE);
            File file = resource.getFile();
            csvReader = new CSVReader(new FileReader(file));
            log.info("Data was accessed successfully");
        } catch (IOException e) {
            log.error("Error while accessing the core Dataset, " + e.getMessage(), e);
        }
        return csvReader;
    }
}
