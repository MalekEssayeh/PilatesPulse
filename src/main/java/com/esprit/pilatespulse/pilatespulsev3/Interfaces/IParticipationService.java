package com.esprit.pilatespulse.pilatespulsev3.Interfaces;

import com.esprit.pilatespulse.pilatespulsev3.Models.User;

public interface IParticipationService<T> {

    public void participate(T t, User user);
    public void deleteParticipation(T t, int id);
    public void displayParticipation(T t);
}
