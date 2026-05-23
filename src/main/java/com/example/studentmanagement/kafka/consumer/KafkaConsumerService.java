package com.example.studentmanagement.kafka.consumer;

import com.example.studentmanagement.kafka.utils.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaTopics.NEW_STUDENT_TOPIC, groupId = "my-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
