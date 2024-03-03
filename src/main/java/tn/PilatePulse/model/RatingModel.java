package tn.PilatePulse.model;

public class RatingModel {
    private int idRating;
    private int idProduct;
    private int idUser;
    private double nbStars;

   public RatingModel() {
    }

    public RatingModel(int idProduct, int idUser, double nbStars) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.nbStars = nbStars;
    }

    public RatingModel(int idRating, int idProduct, int idUser, double nbStars) {
        this.idRating = idRating;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.nbStars = nbStars;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getNbStars() {
        return nbStars;
    }

    public void setNbStars(int nbStars) {
        this.nbStars = nbStars;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "idRating=" + idRating +
                ", idProduct=" + idProduct +
                ", idUser=" + idUser +
                ", nbStars=" + nbStars +
                '}';
    }
}
