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
        ProgrammeService programmeService=new ProgrammeService();
        ExerciceService exerciceService=new ExerciceService();
        Exercice e=new Exercice( 68,12,5,  "Difficile", "Preacher curl", "Dos", "link");
        Exercice e1=new Exercice( 69,12,5,  "Facile", "Skull crusher", "Dos", "link");
        Exercice e3=new Exercice( 70,12,5,  "Moyenne", "Bench press", "Dos", "link");
        Exercice e2=new Exercice( 71,12,5,  "Moyenne", "Skull crusher", "Dos", "link");
        Exercice e4=new Exercice( 72,12,5,  "Facile", "Lat pulldowns", "Dos", "link");
        Exercice e5=new Exercice( 73,14,5,  "Facile", "Squats", "Dos", "link");



        exerciceService.add(e);
        exerciceService.add(e1);
        exerciceService.add(e2);
        exerciceService.add(e3);
        exerciceService.add(e4);
        exerciceService.add(e5);


List<Exercice> Exercices=new ArrayList<>();
        Exercices.add(e);
        Exercices.add(e1);
        Exercices.add(e2);
        Exercices.add(e3);
        Exercices.add(e4);
        Exercices.add(e5);




        Programme p=new Programme(210,120,"Burn fat",120,Exercices);
       // Programme p2=new Programme(210,180,"BUILD STRONG ABS",6,"EASY",Exercices);

        programmeService.add(p);
        //programmeService.update(p,"Facile");
        //programmeService.delete(14);
        for(Programme pr :programmeService.fetch()){
            System.out.println(pr);
        }
        /*
for(Exercice ex :exerciceService.fetch()){
    System.out.println(ex);
}
exerciceService.update(e1,"HTTPS/VIDEO.TN");
        for(Exercice ex :exerciceService.fetch()){
            System.out.println(ex);
        }
exerciceService.delete(e);
        for(Exercice ex :exerciceService.fetch()){
            System.out.println(ex);
        }*/
    }
}