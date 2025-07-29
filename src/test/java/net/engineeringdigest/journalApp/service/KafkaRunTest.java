package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.SentimentData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class KafkaRunTest {

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Test
    public void testSendToKafka() {
        SentimentData data = SentimentData.builder()
                .email("mandeepkaursandhu410@gmail.com")
                .sentiment("Feeling gooh this week!")
                .build();

        kafkaTemplate.send("weekly-sentiments", data.getEmail(), data);
    }
}