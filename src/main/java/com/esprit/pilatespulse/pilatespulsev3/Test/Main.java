package com.esprit.pilatespulse.pilatespulsev3.Test;

import com.esprit.pilatespulse.pilatespulsev3.Models.Event;
import com.esprit.pilatespulse.pilatespulsev3.Services.EventService;
import com.esprit.pilatespulse.pilatespulsev3.Services.SessionService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EventService eventService = new EventService();
        SessionService sessionService = new SessionService();

        LocalDate startDate = LocalDate.of(2024, 3, 1);
        LocalDate finishDate = LocalDate.of(2024, 4, 5);

        Event e1 = new Event("Event1",startDate,finishDate,20,"new event",1);
        eventService.addNewEvent(e1);
    }
}
