package PilatesPulse.Models;

import java.util.ArrayList;
import java.util.List;

public class Programme {
    int idProgramme,idCoachp,dureeProgramme,evaluationProgramme;
    String difficulteProgramme,nomProgramme;
    List<Exercice> listExercice = new ArrayList<>();

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public Programme() {
    }

    public Programme(int idCoachp, int dureeProgramme,String nomProgramme, int evaluationProgramme, String difficulteProgramme, List<Exercice> listExercice) {
        this.idCoachp = idCoachp;
        this.dureeProgramme = dureeProgramme;
        this.evaluationProgramme = evaluationProgramme;
        this.difficulteProgramme = difficulteProgramme;
        this.listExercice = listExercice;
        this.nomProgramme=nomProgramme;

    }

    public Programme(int idProgramme, int idCoachp,String nomProgramme, int dureeProgramme, int evaluationProgramme, String difficulteProgramme, List<Exercice> listExercice) {
        this.idProgramme = idProgramme;
        this.idCoachp = idCoachp;
        this.dureeProgramme = dureeProgramme;
        this.evaluationProgramme = evaluationProgramme;
        this.difficulteProgramme = difficulteProgramme;
        this.listExercice = listExercice;
        this.nomProgramme=nomProgramme;

    }

    public Programme(int idCoachp,String nomProgramme, int dureeProgramme, int evaluationProgramme, String difficulteProgramme) {
        this.idCoachp = idCoachp;
        this.dureeProgramme = dureeProgramme;
        this.evaluationProgramme = evaluationProgramme;
        this.difficulteProgramme = difficulteProgramme;
this.nomProgramme=nomProgramme;
    }

    public int getIdProgramme() {
        return idProgramme;
    }

    public void setIdProgramme(int idProgramme) {
        this.idProgramme = idProgramme;
    }

    public int getIdCoachp() {
        return idCoachp;
    }

    public void setIdCoachp(int idCoachp) {
        this.idCoachp = idCoachp;
    }

    public int getDureeProgramme() {
        return dureeProgramme;
    }

    public void setDureeProgramme(int dureeProgramme) {
        this.dureeProgramme = dureeProgramme;
    }

    public int getEvaluationProgramme() {
        return evaluationProgramme;
    }

    public void setEvaluationProgramme(int evaluationProgramme) {
        this.evaluationProgramme = evaluationProgramme;
    }

    public String getDifficulteProgramme() {
        return difficulteProgramme;
    }

    public void setDifficulteProgramme(String difficulteProgramme) {
        this.difficulteProgramme = difficulteProgramme;
    }

    public List<Exercice> getListExercice() {
        return listExercice;
    }

    public void setListExercice(List<Exercice> listExercice) {
        this.listExercice = listExercice;
    }

    @Override
    public String toString() {
        return "Programme{" +
                "idProgramme=" + idProgramme +
                ", idCoachp=" + idCoachp +
                ", dureeProgramme=" + dureeProgramme +
                ", evaluationProgramme=" + evaluationProgramme +
                ", difficulteProgramme=" + difficulteProgramme +
                ", listExercice=" + listExercice +
                '}';
    }
}
