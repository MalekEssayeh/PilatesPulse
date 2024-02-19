package PilatesPulse.Models;

import java.util.Objects;

public class Exercice {
    int idExercice,idCoach,evaluationExercice;
    String difficulteExercice,nomExercice,muscle,demonstration;

    public Exercice(int idCoach, int evaluationExercice, String difficulteExercice, String nomExercice, String muscle, String demonstration) {
        this.idCoach = idCoach;
        this.evaluationExercice = evaluationExercice;
        this.difficulteExercice = difficulteExercice;
        this.nomExercice = nomExercice;
        this.muscle = muscle;
        this.demonstration = demonstration;
    }

    public Exercice() {
    }

    public Exercice(int idExercice, int idCoach, int evaluationExercice, String difficulteExercice, String nomExercice, String muscle, String demonstration) {
        this.idExercice = idExercice;
        this.idCoach = idCoach;
        this.evaluationExercice = evaluationExercice;
        this.difficulteExercice = difficulteExercice;
        this.nomExercice = nomExercice;
        this.muscle = muscle;
        this.demonstration = demonstration;
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public int getEvaluationExercice() {
        return evaluationExercice;
    }

    public void setEvaluationExercice(int evaluationExercice) {
        this.evaluationExercice = evaluationExercice;
    }

    public String getDifficulteExercice() {
        return difficulteExercice;
    }

    public void setDifficulteExercice(String difficulteExercice) {
        this.difficulteExercice = difficulteExercice;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getDemonstration() {
        return demonstration;
    }

    public void setDemonstration(String demonstration) {
        this.demonstration = demonstration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercice exercice = (Exercice) o;
        return idExercice == exercice.idExercice && idCoach == exercice.idCoach && evaluationExercice == exercice.evaluationExercice && Objects.equals(difficulteExercice, exercice.difficulteExercice) && Objects.equals(nomExercice, exercice.nomExercice) && Objects.equals(muscle, exercice.muscle) && Objects.equals(demonstration, exercice.demonstration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExercice, idCoach, evaluationExercice, difficulteExercice, nomExercice, muscle, demonstration);
    }

    @Override
    public String toString() {
        return nomExercice;


    }



}
