package tn.PilatePulse.model;

public class WishList {

    private int idProduct;
    private String nameProduct;
    private float priceProduct;
    private String productDescription;
    private String image;

    public WishList() {
    }

    public WishList(String nameProduct, float priceProduct, String productDescription, String image) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.productDescription = productDescription;
        this.image = image;
    }

    public WishList(int idProduct, String nameProduct, float priceProduct, String productDescription, String image) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.productDescription = productDescription;
        this.image = image;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", productDescription='" + productDescription + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
