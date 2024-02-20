package org.esprit.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Livraison {
    private int idLiv, phone;
    private String methodePay, adresseLiv;
    private Date dateLiv;
    List<Commande> listCommande = new ArrayList<>();



    public Livraison(){

    }



    public Livraison(String methodePay, String adresseLiv, Date dateLiv, int phone) {
        this.methodePay = methodePay;
        this.adresseLiv = adresseLiv;
        this.dateLiv = dateLiv;
        this.phone = phone;
    }

    public Livraison(int idLiv, String methodePay, String adresseLiv, Date dateLiv, int phone, List<Commande> listCommande) {
        this.idLiv = idLiv;

        int cashOnDelivery = 0, creditCard = 0;
        for (Commande c : listCommande) {
            if ("Cash on delivery".equals(methodePay)) {
                cashOnDelivery++;
            } else if ("creditCard".equals(methodePay)) {
                creditCard++;
            }
        }
        this.adresseLiv = adresseLiv;
        this.dateLiv = dateLiv;
        this.phone = phone;
        this.listCommande = listCommande;
    }

    public int getIdLiv() {
        return idLiv;
    }

    public void setIdLiv(int idLiv) {
        this.idLiv = idLiv;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getMethodePay() {
        return methodePay;
    }

    public void setMethodePay(String methodePay) {
        this.methodePay = methodePay;
    }

    public String getAdresseLiv() {
        return adresseLiv;
    }

    public void setAdresseLiv(String adresseLiv) {
        this.adresseLiv = adresseLiv;
    }

    public Date getDateLiv() {
        return dateLiv;
    }

    public void setDateLiv(Date dateLiv) {
        this.dateLiv = dateLiv;
    }

    public List<Commande> getListCommande() {
        return listCommande;
    }

    public void setListCommande(List<Commande> listCommande) {
        this.listCommande = listCommande;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "idLiv=" + idLiv +
                ", phone=" + phone +
                ", methodePay='" + methodePay + '\'' +
                ", adresseLiv='" + adresseLiv + '\'' +
                ", dateLiv=" + dateLiv +
                ", listCommande=" + listCommande +
                '}';
    }
}
