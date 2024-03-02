package tn.PilatePulse.model;

public class Rating {
    private int idRating;
    private int idProduct;
    private int idUser;
    private int nbStars;

    public Rating() {
    }

    public Rating(int idProduct, int idUser, int nbStars) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.nbStars = nbStars;
    }

    public Rating(int idRating, int idProduct, int idUser, int nbStars) {
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

    public int getNbStars() {
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
