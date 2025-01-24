package com.larissa.services;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateFormatter {
    private static final int START_HOUR = 10; // horario do dia que irá começar o evento
    private static final int END_HOUR = 11;
    public static EventDateTime[] setEventDate(LocalDate localDate){
        LocalDateTime initialLocal = localDate.atTime(START_HOUR, 0);
        LocalDateTime endLocal = localDate.atTime(END_HOUR, 0);

        Instant startInstant = initialLocal.atZone(ZoneId.systemDefault()).toInstant();
        Instant endInstant = endLocal.atZone(ZoneId.systemDefault()).toInstant();

        DateTime start = new DateTime(startInstant.toEpochMilli());
        EventDateTime startEventDateTime = new EventDateTime()
                .setDateTime(start)
                .setTimeZone(ZoneId.systemDefault().toString());
        DateTime end = new DateTime(endInstant.toEpochMilli());
        EventDateTime endEventDateTime = new EventDateTime()
                .setDateTime(end)
                .setTimeZone(ZoneId.systemDefault().toString());

        EventDateTime[] eventDateTimes = new EventDateTime[2];
        eventDateTimes[0] = startEventDateTime;
        eventDateTimes[1] = endEventDateTime;
        return eventDateTimes;
    }
}
