package com.chrisshayan.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    public static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return countDownLatch;
    }

    @KafkaListener(topics = "${kafka.topic.boot}")
    public void receive(ConsumerRecord<?, ?> record) {
        LOGGER.info("received payload='{}'", record.toString());
        LOGGER.info("received payload.value='{}'", record.value());
        countDownLatch.countDown();
    }
}
