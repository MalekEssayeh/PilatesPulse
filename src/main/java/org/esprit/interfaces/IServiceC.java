package org.esprit.interfaces;
import java.sql.SQLException;
import java.util.List;

public interface IServiceC<T> {
 public void ajouter(T t) throws SQLException;


 //list : select
 public List<T> fetch();



 public void modifier(T t, String a);


 public void supprimer(int t);
}















