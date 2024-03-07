package tn.PilatePulse.model;

public class Product {
    private int idProduct;
    private String nameProduct;
    private float priceProduct;
    private int idCategory;
    private String productDescription;
    private int stock;
    private String categoryName;
    private String image;


    public Product(){

    }

    public Product(int idProduct, String nameProduct,String productDescription,String image, float priceProduct, int stock, int idCategory) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.idCategory = idCategory;
        this.productDescription = productDescription;
        this.image=image;
        this.stock = stock;
    }

    public Product(String nameProduct,String productDescription,String image, float priceProduct, int stock, int idCategory) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.idCategory = idCategory;
        this.productDescription = productDescription;
        this.image=image;
        this.stock = stock;
    }

    public Product(String nameProduct,String productDescription,String image, float priceProduct, int stock, String categoryName) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.productDescription = productDescription;
        this.image=image;
        this.stock = stock;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", idCategory=" + idCategory +
                ", productDescription='" + productDescription + '\'' +
                ", stock=" + stock +
                ", image='" + image + '\'' +
                '}';
    }
}
