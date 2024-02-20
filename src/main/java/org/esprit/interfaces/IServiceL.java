package org.esprit.interfaces;

import org.esprit.models.Commande;

import java.sql.SQLException;
import java.util.List;

public interface IServiceL<T> {

    public void ajouter(T t) throws SQLException;

    //list : select
    public List<T> fetchLivraisons();



    public void modifier(T t, String a);

    public void supprimer(int t);
}
