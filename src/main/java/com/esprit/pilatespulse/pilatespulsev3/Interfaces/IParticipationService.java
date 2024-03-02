package com.esprit.pilatespulse.pilatespulsev3.Interfaces;

public interface IParticipationService<T> {

    public void addParticipation(T t);
    public void deleteParticipation(T t, int id);
    public void displayParticipation(int id);
}
