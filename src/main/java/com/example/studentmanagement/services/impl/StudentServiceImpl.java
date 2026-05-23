package com.example.studentmanagement.services.impl;

import com.example.studentmanagement.kafka.producer.KafkaProducerService;
import com.example.studentmanagement.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final KafkaProducerService kafkaProducer;

    @Override
    public void createStudent(String message) {
        kafkaProducer.sendMessage(message);
    }
}
