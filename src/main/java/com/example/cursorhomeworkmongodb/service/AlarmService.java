package com.example.cursorhomeworkmongodb.service;

import com.example.cursorhomeworkmongodb.model.Alarm;
import com.example.cursorhomeworkmongodb.repo.AlarmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepo alarmRepo;

    public Alarm add(Alarm alarm) {
        return alarmRepo.save(alarm);
    }

    public Alarm updateAlarmTime(Alarm alarm) {
        Alarm alarmEdit = alarmRepo.findById(alarm.getId()).orElse(null);
        assert alarmEdit != null;
        alarmEdit.setAlarmTime(alarm.getAlarmTime());
        return alarmRepo.save(alarmEdit);
    }

    public Alarm updateAlarmSchedule(Alarm alarm) {
        Alarm alarmEdit = alarmRepo.findById(alarm.getId()).orElse(null);
        assert alarmEdit != null;
        alarmEdit.setAlarmSchedule(alarm.getAlarmSchedule());
        return alarmRepo.save(alarmEdit);
    }

    public Alarm getById(BigInteger id) {
        return alarmRepo.findById(id).orElse(null);
    }

    public void deleteById(BigInteger id) {
        alarmRepo.deleteById(id);
    }

    public List<Alarm> getAll() {
        return alarmRepo.findAll();
    }
}
