package org.esprit.models;

import java.sql.Date;

public class Commande {
    private int idCmd, idUser,Total;
    private String  codePromo, nomProd;


    public Commande() {

    }

//    public Commande(int idCmd, int idUser, int total, String codePromo, String nomProd) {
//        this.idCmd = idCmd;
//        this.idUser = idUser;
//        Total = total;
//        this.codePromo = codePromo;
//        this.nomProd = nomProd;
//    }

    public Commande(int total, String codePromo, String nomProd) {
        Total = total;
        this.codePromo = codePromo;
        this.nomProd = nomProd;
    }

    public int getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(int idCmd) {
        this.idCmd = idCmd;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }


    @Override
    public String toString() {
        return "Commande{" +
                "idCmd=" + idCmd +
                ", idUser=" + idUser +
                ", Total=" + Total +
                ", codePromo='" + codePromo + '\'' +
                ", nomProd='" + nomProd + '\'' +
                '}';
    }
}