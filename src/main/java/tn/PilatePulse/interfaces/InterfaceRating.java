package tn.PilatePulse.interfaces;

import java.util.List;

public interface InterfaceRating <T> {
    public void addRating(T t);
    public void updateRating(T t);
    public void removeRating(int id);
    public List<T> fetchRating();
}
