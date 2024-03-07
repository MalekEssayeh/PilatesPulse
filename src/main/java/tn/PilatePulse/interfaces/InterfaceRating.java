package tn.PilatePulse.interfaces;

import java.util.List;
import java.util.Map;

public interface InterfaceRating <T> {
    public void addRating(T t);
    public void updateRating(T t);
    public boolean ratingExists(int idProduct,int idUser);
    public void removeRating(int id);
    public List<T> fetchRating();
    public Map<String, Double> fetchProductRatings();
    public Map<String, Double> fetchAverageProductRatings();
}
