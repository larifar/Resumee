package com.larissa.records;

import java.time.LocalDate;

public record CalendarEvent(
        String summary, String Location, String description, LocalDate initialDay
) {
}
