package de.vwgis.kafkatitanixexercise.kafka.producer;


import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@CommonsLog(topic = "PassengerConsumer Logger")
@Service
public class GenericDataProducer {


    public void publish(Map<String, String> data) {

        Resource resource = new ClassPathResource("avro/passenger.avsc");
        Schema.Parser parser = new Schema.Parser();
        Schema schema = null;
        try {
            File avroSchema = resource.getFile();
            schema = parser.parse(avroSchema);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GenericRecord avroRecord = new GenericData.Record(schema);
        avroRecord.put("passengerId", 1337);
        avroRecord.put("pClass", 65);
        avroRecord.put("survived", true);
        avroRecord.put("name", "testHanz");
        avroRecord.put("sex", "male");
        avroRecord.put("age", 37.7d);
        avroRecord.put("sibSp", 77);
        avroRecord.put("parch", 88);
        avroRecord.put("ticket", "ticket");
        avroRecord.put("fare", 3.4d);
        avroRecord.put("cabin", "cabin");
        avroRecord.put("embarked", "embarked");

        Producer<String, GenericRecord> producer = createProducer();

        //prepare the kafka record
        ProducerRecord<String, GenericRecord> record = new ProducerRecord<>("passenger", null, avroRecord);

        producer.send(record);
        //ensures record is sent before closing the producer
        producer.flush();

        producer.close();
        log.info("generic data published");

    }


    private Producer<String, GenericRecord> createProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        return new KafkaProducer<>(properties);
    }


}
