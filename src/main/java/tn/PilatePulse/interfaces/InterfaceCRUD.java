package tn.PilatePulse.interfaces;

import tn.PilatePulse.model.Product;

import java.util.List;

public interface InterfaceCRUD <T>{
    //CRUD
    public void add(T t);
    public void update(T t);
    public void remove(int id);

    //Recherche
    public List<T> rechercheParNom(String nom);
    public List<T> rechercheParId(int id);
 }
