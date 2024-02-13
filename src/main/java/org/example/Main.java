package org.example;

import org.example.Models.Coach;
import org.example.Models.Event;
import org.example.Models.Seance;
import org.example.Services.ServiceEvent;
import org.example.Services.ServiceSeance;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Coach nada = new Coach("nada","ben abdallah", "nadaba@gmail.Com","nada1111");

        List<Event> events = new ArrayList<>();
        List<Seance> seances = new ArrayList<>();

        ServiceEvent serviceEvent = new ServiceEvent();
        ServiceSeance serviceSeance = new ServiceSeance();

        Event e1 = new Event("Pilates Class",
                LocalDate.of(2022, 2, 14),
                Time.valueOf("10:00:00"),
                Time.valueOf("11:30:00"),
                20,
                "Pilates class for all levels.");


        Event e2 = new Event("Pilates Class 2",
                LocalDate.of(2022, 2, 14),
                Time.valueOf("10:00:00"),
                Time.valueOf("11:30:00"),
                40,
                "Pilates class for all levels.");

        Seance s1 = new Seance(120,1,1);
        Seance s2 = new Seance(90,0,2);

        System.out.println("------------Gestion Event-----------");

        System.out.println("------------Ajouter Event-----------");

        serviceEvent.ajouter(e1);
        serviceEvent.ajouter(e2);

        for (Event e : serviceEvent.afficher()) {
            System.out.println(e);
        }


        System.out.println("------------Gestion Seance-----------");

        System.out.println("------------Ajouter Seance-----------");
        serviceSeance.ajouter(s1);
       serviceSeance.ajouter(s2);

        for (Seance s : serviceSeance.afficher()) {
            System.out.println(s);
        }

        serviceSeance.supprimer(18);
        serviceSeance.supprimer(20);


    }

}
