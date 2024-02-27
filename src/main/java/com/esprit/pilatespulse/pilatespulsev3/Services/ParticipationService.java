package com.esprit.pilatespulse.pilatespulsev3.Services;

import com.esprit.pilatespulse.pilatespulsev3.Utils.MyDB;

import java.sql.Connection;

public class ParticipationService {
    private Connection cnx = MyDB.getInstance().getCnx();
}
