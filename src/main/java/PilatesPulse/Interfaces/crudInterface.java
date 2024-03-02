package PilatesPulse.Interfaces;
import javafx.collections.ObservableList;

import java.util.List;

public interface crudInterface <T>{






        //add
        public void add(T p);

        //list : select
        public ObservableList<T> fetch();
        public void update(T p,String a);
        public void delete(int p);
        //affectation

    }

