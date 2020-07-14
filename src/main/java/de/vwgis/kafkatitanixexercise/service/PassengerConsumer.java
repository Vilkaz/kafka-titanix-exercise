//package de.vwgis.kafkatitanixexercise.service;
//
//import de.vwgis.kafkatitanixexercise.model.Passenger;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PassengerConsumer {
//
//    private final Logger logger = LoggerFactory.getLogger(PassengerConsumer.class);
//
//    @Value("${topic.name}")
//    private String TOPIC;
//
//    @KafkaListener(topics = "passenger", groupId = "passengerConsumers")
//    public void consume(ConsumerRecords<String, Passenger> record) {
//        record.forEach(r -> {
//            System.out.println();
//        });
//        logger.info("consumed message from kafka: " + record);
//    }
//
//}
