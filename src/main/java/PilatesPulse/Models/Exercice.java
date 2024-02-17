package PilatesPulse.Models;

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
    public String toString() {
        return "<b>ID:</b> <font color='#4B0082'>" + idExercice + "</font>\n" +
                "<b>Coach ID:</b> <font color='#4B0082'>" + idCoach + "</font>\n" +
                "<b>Evaluation:</b> <font color='#4B0082'>" + evaluationExercice + "</font>\n" +
                "<b>Difficulty:</b> <font color='#4B0082'>" + difficulteExercice + "</font>\n" +
                "<b>Name:</b> <font color='#4B0082'>" + nomExercice + "</font>\n" +
                "<b>Muscle:</b> <font color='#4B0082'>" + muscle + "</font>\n" +
                "<b>Demonstration:</b> <font color='#4B0082'>" + demonstration + "</font>";
    }



}
