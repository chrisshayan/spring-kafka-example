package com.chrisshayan.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import com.chrisshayan.kafka.consumer.Receiver;
import com.chrisshayan.kafka.producer.Sender;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTest {
    private static String BOOT_TOPIC = "boot.t";

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @ClassRule
    public static KafkaEmbedded embedded = new KafkaEmbedded(1, true, BOOT_TOPIC);

    @Test
    public void testReceive() throws Exception {
        sender.send(BOOT_TOPIC, "Hello Kafka Boot");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
