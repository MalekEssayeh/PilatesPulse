package tn.PilatePulse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {

    static final String URL ="jdbc:mysql://localhost:3306/pilatespulse";
    static final String USER ="root";
    static final String PWD ="";


//var
private Connection cnx;
static MaConnexion instance;

//constructeur

//*1*
public MaConnexion(){
    try{
        cnx = DriverManager.getConnection(URL,USER,PWD);
        System.out.println("Connection established");
    }catch (SQLException sqlException){
        sqlException.printStackTrace();
    }
}

//*2*
public Connection getCnx(){
    return cnx;
}

//*3* add unique connection
public static MaConnexion getInstance(){
    if (instance == null)
        instance = new MaConnexion();
    return instance;
}

}
