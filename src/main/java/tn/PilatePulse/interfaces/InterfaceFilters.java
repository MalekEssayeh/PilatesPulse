package tn.PilatePulse.interfaces;

import tn.PilatePulse.model.Product;

import java.util.List;

public interface InterfaceFilters <T>{
    public List<T> filterProductsByIdCategory(int categoryId);
    public List<T> filterProductsByCategoryName(String categoryName);
    public List<T> filterProductsByPriceRange(float minPrice, float maxPrice);
}
