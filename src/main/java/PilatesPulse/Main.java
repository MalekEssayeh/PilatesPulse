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
        Exercice e=new Exercice( 21,12,5,  "Moyenne", "preacher curl", "Dos", "link");
        Exercice e1=new Exercice( 22,12,5,  "Moyenne", "Skull crusher", "Dos", "link");
        Exercice e3=new Exercice( 652,12,5,  "Moyenne", "preacher curl", "Dos", "link");
        Exercice e2=new Exercice( 255,12,5,  "Moyenne", "Skull crusher", "Dos", "link");



       /* exerciceService.add(e);
        exerciceService.add(e1);*/

List<Exercice> Exercices=new ArrayList<>();
Exercices.add(e);
Exercices.add(e1);



        Programme p=new Programme(211,120,"Burn fat",3,"hard",Exercices);
       // Programme p2=new Programme(210,180,"BUILD STRONG ABS",6,"EASY",Exercices);

        programmeService.add(p);
        programmeService.update(p,"Facile");
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