package com.esprit.pilatespulse.pilatespulsev3.Test;

import com.esprit.pilatespulse.pilatespulsev3.Models.Event;
import com.esprit.pilatespulse.pilatespulsev3.Models.Session;
import com.esprit.pilatespulse.pilatespulsev3.Services.EventService;
import com.esprit.pilatespulse.pilatespulsev3.Services.SessionService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        EventService eventService = new EventService();
        SessionService sessionService = new SessionService();

        LocalDate startDate = LocalDate.of(2024, 3, 1);
        LocalDate finishDate = LocalDate.of(2024, 4, 5);

        /*Event e1 = new Event("Event1",startDate,finishDate,20,"new event",1);
        eventService.addNewEvent(e1);
        eventService.updateEvent(e1,2);
        eventService.deleteEvent(3);*/
        System.out.println(eventService.displayEvents());

        LocalTime startTime = LocalTime.of(10,30,00);
        /*Session s1 = new Session("session 1", "new session", 120, 1, 1, startTime, startDate);
        sessionService.addSession(s1);
        sessionService.updateSession(s1,1);
        sessionService.deleteSession(1);*/
        System.out.println(sessionService.displaySessions());
    }
}
