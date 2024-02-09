package PilatesPulse;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Services.ProgrammeService;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ProgrammeService programmeService = new ProgrammeService();
        ExerciceService exerciceService = new ExerciceService();
        Exercice e = new Exercice(68, 12, 5, "Difficile", "Preacher curl", "Bras", "link1");
        Exercice e1 = new Exercice(69, 12, 5, "Difficile", "Skull crusher", "Bras", "link2");
        Exercice e3 = new Exercice(70, 12, 5, "Moyenne", "Bench press", "Dos", "link3");
        Exercice e2 = new Exercice(71, 12, 5, "Difficile", "Skull crusher", "Bras", "link4");
        Exercice e4 = new Exercice(72, 12, 5, "Facile", "Lat pulldowns", "StabilisateurEpaule", "link5");
        Exercice e5 = new Exercice(73, 14, 5, "Facile", "Squats", "Fessiers", "link6");

        exerciceService.add(e);
        exerciceService.add(e1);
        exerciceService.add(e2);
        exerciceService.add(e3);
        exerciceService.add(e4);
        exerciceService.add(e5);
        for (Exercice ex : exerciceService.fetch()) {
            System.out.println(ex);
        }
        exerciceService.update(e1, "HTTPS/VIDEO.TN");
        for (Exercice ex : exerciceService.fetch()) {
            System.out.println(ex);
        }
        exerciceService.delete(e.getIdExercice());
        for (Exercice ex : exerciceService.fetch()) {
            System.out.println(ex);
        }
        List<Exercice> Exercices = new ArrayList<>();
        Exercices.add(e1);
        Exercices.add(e2);

        Programme p = new Programme(210, 120, "Burn fat", 120, Exercices);

        Exercices.add(e3);
        Exercices.add(e4);
        Programme p2 = new Programme(245, 210, "BUILD STRONG ABS", 80, Exercices);

        programmeService.add(p);
        programmeService.add(p2);
        for (Programme pr : programmeService.fetch()) {
            System.out.println(pr);
        }
        programmeService.update(p, "Facile");
        for (Programme pr : programmeService.fetch()) {
            System.out.println(pr);
        }
        programmeService.delete(210);

        for (Programme pr : programmeService.fetch()) {
            System.out.println(pr);
        }
        programmeService.addList(p, e5);
        for (Programme pr : programmeService.fetch()) {
            System.out.println(pr);
        }
        for (Exercice ex : exerciceService.rechercheExercice(72)) {
            System.out.println(ex);
        }
        for (Programme ex : programmeService.rechercheProgramme(210)) {
            System.out.println(ex);
        }

    }

}