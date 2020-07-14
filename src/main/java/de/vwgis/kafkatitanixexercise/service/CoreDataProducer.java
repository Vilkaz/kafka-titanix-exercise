package de.vwgis.kafkatitanixexercise.service;

import com.opencsv.CSVReader;
import de.vwgis.kafkatitanixexercise.model.Passenger;
import de.vwgis.kafkatitanixexercise.model.Topiclist;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Future;

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
//        CsvToBean csv = new CsvToBeanBuilder(createCSVReader())
//                .withType(Passenger.class)
//                .build();
//        List<Passenger> passengers = csv.parse();

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        Producer<String, SpecificRecord> producer = new KafkaProducer<>(properties);

        Passenger passenger = Passenger.newBuilder()
                .setPassengerId(1)
                .setSurvived(true)
                .setAge(25)
                .setCabin("a")
                .setEmbarked("yes?")
                .setFare(12)
                .setName("TestPassenger")
                .setParch(123)
                .setPClass(1)
                .setSex("male")
                .setSibSp(456)
                .setTicket("ticket1")
                .setPClass(3)
                .build();

        sendMessage(passenger);

        final ProducerRecord<String, SpecificRecord> record = new ProducerRecord<>(
                Topiclist.PASSENGERS,
                Integer.toString(passenger.getPassengerId()), passenger
        );
        Future<RecordMetadata> send = producer.send(record);
        producer.flush();
        producer.close();
        System.out.println(send);
    }

    void sendMessage(Passenger passenger) {
        this.kafkaTemplate.send(Topiclist.PASSENGERS, String.valueOf(passenger.getPassengerId()), passenger);
        log.info(String.format("Produced user -> %s", passenger));
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
