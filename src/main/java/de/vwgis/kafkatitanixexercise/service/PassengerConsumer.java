package de.vwgis.kafkatitanixexercise.service;

import de.vwgis.kafkatitanixexercise.model.Passenger;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@CommonsLog(topic = "PassengerConsumer Logger")
@Service
public class PassengerConsumer {

    @Value("${topic.name}")
    private String TOPIC;

    @KafkaListener(topics = "passenger", groupId = "passengerConsumers")
    public void consume(ConsumerRecord<String, Passenger> records) {
        Passenger value = records.value();
        log.info("consumed message from kafka: " + records);
    }

}
