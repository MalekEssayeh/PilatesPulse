package tn.PilatePulse.interfaces;

import tn.PilatePulse.model.Rating;

import java.util.List;

public interface InterfaceRating <T> {
    public void addRating(T t);
    public void updateRating(T t);
    public Rating getRatingById(int id);

}
