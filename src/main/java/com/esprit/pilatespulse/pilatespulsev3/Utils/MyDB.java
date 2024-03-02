package com.esprit.pilatespulse.pilatespulsev3.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    private static MyDB instance ;
    final String URL ="jdbc:mysql://127.0.0.1:3306/gestionevent";
    final String USERNAME ="root";
    final String PWD ="";
    private Connection cnx;

    private MyDB(){
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("connected ......");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public  Connection getCnx (){
        return this.cnx;
    }
    public static MyDB getInstance (){
        if (instance == null )
            instance = new MyDB();

        return instance;
    }

}