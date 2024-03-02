package tn.PilatePulse.services;

import tn.PilatePulse.interfaces.InterfaceCRUD;
import tn.PilatePulse.interfaces.InterfaceFilters;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements InterfaceCRUD<Product>, InterfaceFilters<Product> {
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void add(Product product) {
        try {
            String req = "INSERT INTO `Product`(`nameProduct`,`productDescription`,`image`, `PriceProduct`,`stock` ,`idCategory`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, product.getNameProduct());
            ps.setString(2, product.getProductDescription());
            ps.setString(3, product.getImage());
            ps.setFloat(   4, product.getPriceProduct());
            ps.setInt(5, product.getStock());
            ps.setInt(6, product.getIdCategory());

            ps.executeUpdate();

            System.out.println("Product : "+"*"+product.getNameProduct()+"*"+ "added succesfuly");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public void add2(String nom, float prix, int idCat ) {
        try {
            String req = "INSERT INTO `Product`(`nameProduct`, `PriceProduct`, `idCategory`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            ps.setFloat(   2, prix);
            ps.setInt(3, idCat);

            ps.executeUpdate();

            System.out.println("Product : "+"*"+nom+"*"+ "added succesfuly");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public List<Product> fetchProduct (){
        List<Product> products = new ArrayList<>();
        try{
            String req = "SELECT * FROM product";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Product p = new Product();
                p.setIdProduct(rs.getInt(1));
                p.setNameProduct(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setProductDescription(rs.getString(4));
                p.setPriceProduct(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setIdCategory(rs.getInt(7));

                products.add(p);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return products;
    }

    public List<Product> fetchProductByCategoryName(){
        List<Product> products = new ArrayList<>();
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery("SELECT p.idProduct, p.nameProduct, p.image, p.productDescription, p.priceProduct, p.stock, c.nameCategory " +
                     "FROM product p " +
                     "JOIN category c ON p.idCategory = c.idCategory")) {
            while (rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getInt("idProduct"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setImage(rs.getString("image"));
                p.setProductDescription(rs.getString("productDescription"));
                p.setPriceProduct(rs.getFloat("priceProduct"));
                p.setStock(rs.getInt("stock"));
                p.setCategoryName(rs.getString("nameCategory")); // Assuming you have a setter for categoryName in Product class

                products.add(p);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return products;
    }


    @Override
    public void update(Product product) {
        try {
            String req = "UPDATE `product` SET `nameProduct`=?,`image`=?,`productDescription`=?,`PriceProduct`= ?,`stock`= ?,`idCategory`= ? WHERE `idProduct`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, product.getNameProduct());
            ps.setString(2, product.getImage());
            ps.setString(3, product.getProductDescription());
            ps.setFloat(4, product.getPriceProduct());
            ps.setInt(5, product.getStock());
            ps.setInt(6, product.getIdCategory());
            ps.setInt(7, product.getIdProduct());

            //System.out.println(product.getIdProduct());

            ps.executeUpdate();

            System.out.println("Product *"+product.getNameProduct()+"* updated successfuly");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void remove(int idP) {
        try {
            String req ="DELETE FROM `product` WHERE idProduct = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,idP);
            ps.executeUpdate();
            System.out.println("Product removed");
        }catch (SQLException exp){
            exp.printStackTrace();
        }
    }

    public List<Product> rechercheParId(int idP){
        List<Product> productList = new ArrayList<>();
        try {

            String req = "SELECT * FROM product WHERE IdProduct LIKE CONCAT(?, '%')";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getInt(1));
                p.setNameProduct(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setProductDescription(rs.getString(4));
                p.setPriceProduct(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setIdCategory(rs.getInt(7));

                productList.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (productList.isEmpty()){
            System.out.println("product unvailable");
        }
        return productList ;
    }

   public List<Product> rechercheParNom(String nomProduit){
       List<Product> productList = new ArrayList<>();
       try {

           String req = "SELECT * FROM product WHERE nameProduct LIKE ?";
           PreparedStatement st = cnx.prepareStatement(req);
           st.setString(1, nomProduit);
           ResultSet rs = st.executeQuery();
           while (rs.next()) {
               Product p = new Product();
               p.setIdProduct(rs.getInt(1));
               p.setNameProduct(rs.getString(2));
               p.setImage(rs.getString(3));
               p.setProductDescription(rs.getString(4));
               p.setPriceProduct(rs.getFloat(5));
               p.setStock(rs.getInt(6));
               p.setIdCategory(rs.getInt(7));


               productList.add(p);
           }

       } catch (SQLException ex) {
           ex.printStackTrace();
       }

       if (productList.isEmpty()){
           System.out.println("product unvailable");
       }
       return productList ;
   }

    @Override
    public List<Product> filterProductsByIdCategory(int categoryId) {
        List<Product> productList = new ArrayList<>();
        try {
            String req ="SELECT idProduct, nameProduct, productDescription, priceProduct, stock, idCategory " +
                    "FROM product " +
                    "WHERE idCategory = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setIdProduct(rs.getInt(1));
                p.setNameProduct(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setProductDescription(rs.getString(4));
                p.setPriceProduct(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setIdCategory(rs.getInt(7));


                productList.add(p);
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }
        if (productList.isEmpty()){
            System.out.println("Product category unvailable");
        }
        return productList ;
    }

    public List<Product> filterProductsByCategoryName(String categoryName){
       List<Product> productList = new ArrayList<>();
        try {
            String req ="SELECT p.idProduct, p.nameProduct, p.productDescription, p.priceProduct, p.stock, p.idCategory, c.nameCat " +
            "FROM product p " +
                    "JOIN category c ON p.idCategory = c.idCategory " +
                    "WHERE c.nameCat = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,categoryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setIdProduct(rs.getInt(1));
                p.setNameProduct(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setProductDescription(rs.getString(4));
                p.setPriceProduct(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setIdCategory(rs.getInt(7));


                productList.add(p);
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }
       if (productList.isEmpty()){
           System.out.println("Product category unvailable");
       }
       return productList ;
   }




    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        try {
            String req = "SELECT nameCat FROM category";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("nameCat");
                categoryNames.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryNames;
    }





    @Override
    public List<Product> filterProductsByPriceRange(float minPrice, float maxPrice) {
        List<Product> productList = new ArrayList<>();
        try {
            String req ="SELECT idProduct, nameProduct,image,productDescription, priceProduct, stock, idCategory " +
                    "FROM product " +
                    "WHERE priceProduct BETWEEN ? AND ?";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setFloat(1,minPrice);
            ps.setFloat(2,maxPrice);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Product p = new Product();
                p.setIdProduct(rs.getInt(1));
                p.setNameProduct(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setProductDescription(rs.getString(4));
                p.setPriceProduct(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setIdCategory(rs.getInt(7));


                productList.add(p);
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }
        if (productList.isEmpty()){
            System.out.println("Unavailable products in the chosen price range");
        }
        return productList ;
    }


}
