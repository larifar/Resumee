package com.larissa;

import com.google.api.services.calendar.Calendar;
import com.larissa.records.CalendarEvent;
import com.larissa.services.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.larissa.services.GoogleCalendar.*;

public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        VerifyFiles verifyFiles = new VerifyFiles();
        ReadMd reader = new ReadMd();
        RequestGemini gemini = new RequestGemini();
        CreateMd creator = new CreateMd();
        Calendar service = getCalendarService();

        ArrayList<String> files;
        try{
            files = verifyFiles.verifyFiles();
            System.out.println(files);
            for (String file : files){
                String resume = reader.read(file);
                String response = gemini.request(resume);
                String name = creator.createMd(file, response);
                CalendarEvent event =
                        new CalendarEvent("Revisar resumo dia 1", "Casa", "Resumo " + name, LocalDate.now());
                CalendarEvent event2 =
                        new CalendarEvent("Revisar resumo dia 7", "Casa", "Resumo " + name, LocalDate.now().plusDays(7));
                CalendarEvent event3 =
                        new CalendarEvent("Revisar resumo dia 21", "Casa", "Resumo " + name, LocalDate.now().plusDays(21));
                createEvent(service, event);
                createEvent(service, event2);
                createEvent(service, event3);
                System.out.println(response);
            }
        } catch (ResumeeException e){
            System.out.println(e.getMessage());
        }
    }
}
