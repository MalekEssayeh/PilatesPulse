package org.example.Interfaces;

import org.example.Models.Seance;

import java.sql.SQLException;
import java.util.List;

public interface IServiceSeance<T> {



    public void ajouter(T t);
    public List<T> afficher() ;
    public void modifier (T t);
    public void supprimer(int a);

}
