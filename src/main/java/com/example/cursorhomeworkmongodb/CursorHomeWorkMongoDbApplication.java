package com.example.cursorhomeworkmongodb;

import com.example.cursorhomeworkmongodb.model.Alarm;
import com.example.cursorhomeworkmongodb.model.enums.DayOfWeek;
import com.example.cursorhomeworkmongodb.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class CursorHomeWorkMongoDbApplication {


    private final AlarmService alarmService;

    @Autowired
    public CursorHomeWorkMongoDbApplication(AlarmService alarmService) { this.alarmService = alarmService; }

    public static void main(String[] args) {
        SpringApplication.run(CursorHomeWorkMongoDbApplication.class, args);
    }

    @PostConstruct
    private void createAlarm() {
        alarmService.add(new Alarm(LocalTime.of(06, 30),
                List.of(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)));
        alarmService.add(new Alarm(LocalTime.of(07, 00),
                List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.TUESDAY)));

    }
}
