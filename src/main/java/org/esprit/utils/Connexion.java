package org.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {


    private final String URL = "jdbc:mysql://localhost:3306/pilatecommandedelivery";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static Connexion instance;

     private Connexion(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
//Singleton == une seule instance d'une classe pour limiter l'accee a la base //
    public static Connexion getInstance(){
         if(instance == null)
             instance = new Connexion();
         return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}