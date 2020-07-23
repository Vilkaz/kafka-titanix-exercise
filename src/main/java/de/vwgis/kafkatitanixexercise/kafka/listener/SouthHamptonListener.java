package de.vwgis.kafkatitanixexercise.kafka.listener;

import de.vwgis.kafkatitanixexercise.config.TOPICS;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class SouthHamptonListener {


    @KafkaListener(topics = TOPICS.SOUTHHAMPTON, groupId = "verifier")
    public void verifier(GenericRecord record) {
        log.debug("Southhampton entry " + record);
    }
}
