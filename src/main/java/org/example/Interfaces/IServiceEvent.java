package org.example.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IServiceEvent<T> {

    public void ajouter(T t);
    public List<T> afficher() ;
    public void modifier (T t, int a);
    public void supprimer(int id);

}
