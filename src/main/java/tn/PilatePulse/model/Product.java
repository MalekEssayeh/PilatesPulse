package tn.PilatePulse.model;

public class Product {
    private int idProduct;
    private String nameProduct;
    private float priceProduct;
    private int idCategory;


    public Product(){

    }
    public Product(String maryoul, int i, Category c1) {
    }

    public Product(int idProduct, String nameProduct, float priceProduct, int idCategory) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.idCategory = idCategory;
    }

    public Product(String nameProduct, float priceProduct, int idCategory) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.idCategory = idCategory;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", idCategory=" + idCategory +
                '}';
    }
}
