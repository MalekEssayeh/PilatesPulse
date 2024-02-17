package user.Interfaces;
import user.Models.user;

import java.sql.Date;
import java.util.List;

public interface userInterface <T>{

    public void add(T t);
    public void add2(T t);
    public void update(T t, String nom);
    public void update2(T t, Date validite);
    public void delete(int id);
    public List<T> show();
    public List<T> search(String keyword);
    public List<T> filterByName(String name);

}
