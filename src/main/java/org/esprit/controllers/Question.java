// Question.java

package org.esprit.controllers;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctOption;

    public Question(String question, String optionA, String optionB, String optionC, String correctOption) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getCorrectOption() {
        return correctOption;
    }
}
