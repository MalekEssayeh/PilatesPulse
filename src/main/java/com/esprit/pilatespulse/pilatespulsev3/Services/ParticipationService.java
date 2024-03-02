package com.esprit.pilatespulse.pilatespulsev3.Services;

import com.esprit.pilatespulse.pilatespulsev3.Interfaces.IEventService;
import com.esprit.pilatespulse.pilatespulsev3.Interfaces.IParticipationService;
import com.esprit.pilatespulse.pilatespulsev3.Models.Participation;
import com.esprit.pilatespulse.pilatespulsev3.Utils.MyDB;

import java.sql.Connection;
import java.util.List;

public class ParticipationService implements IParticipationService<Participation> {
    private Connection cnx = MyDB.getInstance().getCnx();


    @Override
    public void addParticipation(Participation participation) {

    }

    @Override
    public void deleteParticipation(Participation participation, int id) {

    }

    @Override
    public void displayParticipation(int id) {

    }
}
