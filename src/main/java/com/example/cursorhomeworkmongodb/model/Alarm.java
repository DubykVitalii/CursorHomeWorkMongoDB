package com.example.cursorhomeworkmongodb.model;

import com.example.cursorhomeworkmongodb.model.enums.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alarms")
public class Alarm {
    @Id
    private BigInteger id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime alarmTime;
    private List<DayOfWeek> alarmSchedule;

    public Alarm(LocalTime alarmTime, List<DayOfWeek> alarmSchedule) {
        this.alarmTime = alarmTime;
        this.alarmSchedule = alarmSchedule;
    }
}
