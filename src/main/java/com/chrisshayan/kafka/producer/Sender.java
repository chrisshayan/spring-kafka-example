package com.chrisshayan.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    public static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    public void send(String topic, String payload) {
        LOGGER.info("sending payload:'{}' to topic='{}'", payload, topic);
        template.send(topic, payload);
    }
}
