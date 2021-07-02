package com.example.cursorhomeworkmongodb.repo;

import com.example.cursorhomeworkmongodb.model.Alarm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface AlarmRepo extends MongoRepository<Alarm, BigInteger> {
}
