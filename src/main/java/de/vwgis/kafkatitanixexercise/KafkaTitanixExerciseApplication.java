package de.vwgis.kafkatitanixexercise;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Arrays;
import java.util.Date;


//@EnableBinding(KafkaStreamsProcessor.class)
@SpringBootApplication
public class KafkaTitanixExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTitanixExerciseApplication.class, args);
    }


//    @StreamListener("input")
//    @SendTo("output")
//    public KStream<?, WordCount> process(KStream<Object, String> input) {
//
//        return input
//                .flatMapValues(
//                        value -> Arrays.asList(value.toLowerCase().split("\\W+")))
//                .map((key, value) -> new KeyValue<>(value, value))
//                .groupByKey()
//                .windowedBy(TimeWindows.of(5000)
//                        .count(Materialized.as("wordcounts"))
//                        .toStream()
//                        .map((key, value) ->
//                                new KeyValue<>(null, new WordCount(key.key(), value));
//    }

}
