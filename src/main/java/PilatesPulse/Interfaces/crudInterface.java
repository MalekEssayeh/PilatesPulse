package PilatesPulse.Interfaces;
import java.util.List;

public interface crudInterface <T>{






        //add
        public void add(T p);

        //list : select
        public List<T> fetch();
        public void update(T p,String a);
        public void delete(int p);
        //affectation

    }

