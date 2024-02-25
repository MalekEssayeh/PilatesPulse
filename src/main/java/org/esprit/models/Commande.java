package org.esprit.models;

import java.sql.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return idCmd == commande.idCmd && idUser == commande.idUser && Total == commande.Total && Objects.equals(codePromo, commande.codePromo) && Objects.equals(nomProd, commande.nomProd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCmd, idUser, Total, codePromo, nomProd);
    }
}