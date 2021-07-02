package com.example.cursorhomeworkmongodb.controller;


import com.example.cursorhomeworkmongodb.model.Alarm;
import com.example.cursorhomeworkmongodb.model.enums.DayOfWeek;
import com.example.cursorhomeworkmongodb.service.AlarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/alarm")
public class AlarmController {
    private final AlarmService alarmService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Alarm>> getAll(
    ) {
        List<Alarm> listAlarm = alarmService.getAll();
        return new ResponseEntity<>(listAlarm, HttpStatus.OK);
    }

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Alarm> getAlarmById(
            @PathVariable("id") BigInteger id
    ) {
        Alarm alarm = alarmService.getById(id);
        return new ResponseEntity<>(alarm, HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Alarm> addAlarm(
            @RequestBody AlarmRequest newAlarm
    ) {
        Alarm alarm = alarmService.add(newAlarm.toAlarm());
        return new ResponseEntity<>(alarm, HttpStatus.OK);
    }

    @PutMapping(value = "/time/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Alarm> updateAlarmTime(@PathVariable("id") BigInteger id,
                                                 @RequestBody TimeUpdateRequest updatedTime) {
        Alarm alarmToEdit = alarmService.getById(id);
        alarmToEdit.setAlarmTime(updatedTime.toAlarm().getAlarmTime());
        alarmService.updateAlarmTime(alarmToEdit);
        Alarm alarmResponse = alarmService.getById(id);
        return new ResponseEntity<>(alarmResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/schedule/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Alarm> updateAlarmSchedule(@PathVariable("id") BigInteger id,
                                                     @RequestBody ScheduleUpdateRequest updateSchedule) {
        Alarm alarmToEdit = alarmService.getById(id);
        alarmToEdit.setAlarmSchedule(updateSchedule.toAlarm().getAlarmSchedule());
        alarmService.updateAlarmSchedule(alarmToEdit);
        Alarm alarmResponse = alarmService.getById(id);
        return new ResponseEntity<>(alarmResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAlarm(@PathVariable("id") BigInteger id) {
        alarmService.deleteById(id);
        return new ResponseEntity<>("Alarm is successfully deleted!", HttpStatus.OK);
    }

    @Data
    private static class AlarmRequest {
        private int alarmHours;
        private int alarmMinutes;
        private List<DayOfWeek> alarmDays;

        private Alarm toAlarm() {
            return new Alarm(LocalTime.of(alarmHours, alarmMinutes), alarmDays);
        }
    }

    @Data
    private static class TimeUpdateRequest {
        private int alarmHours;
        private int alarmMinutes;

        private Alarm toAlarm() {
            return new Alarm(LocalTime.of(alarmHours, alarmMinutes), null);
        }
    }

    @Data
    private static class ScheduleUpdateRequest {
        private List<DayOfWeek> alarmDays;

        private Alarm toAlarm() {
            return new Alarm(null, alarmDays);
        }
    }
}
