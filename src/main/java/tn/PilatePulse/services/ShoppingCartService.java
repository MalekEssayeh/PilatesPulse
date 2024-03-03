package tn.PilatePulse.services;

import tn.PilatePulse.controllers.ShoppingCart;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.ShoppingCartModel;
import tn.PilatePulse.model.WishList;
import tn.PilatePulse.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService {

    Connection cnx = MaConnexion.getInstance().getCnx();


    public List<ShoppingCartModel> fetchProducts (){
            List<ShoppingCartModel> productselected = new ArrayList<>();
            try{
                String req = "SELECT * FROM shoppingcart";
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()){
                    ShoppingCartModel p = new ShoppingCartModel();
                    p.setIdUser(rs.getInt(1));
                    p.setIdProduct(rs.getInt(2));
                    p.setNameProduct(rs.getString(3));
                    p.setImage(rs.getString(4));
                    p.setProductDescription(rs.getString(5));
                    p.setPriceProduct(rs.getFloat(6));
                    p.setQuantity(rs.getInt(7));


                    productselected.add(p);
                }
            } catch (SQLException exception){
                exception.printStackTrace();
            }
            return productselected;
        }

    public void add(Product product, int quantity) {
        try {
            String req = "INSERT INTO `ShoppingCart`(`idUser`,`idProduct`,`nameProduct`,`PriceProduct`,`productDescription`,`image`,`quantity`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, 1);
            ps.setInt(2, product.getIdProduct());
            ps.setString(3, product.getNameProduct());
            ps.setFloat(   4, product.getPriceProduct());
            ps.setString(5, product.getProductDescription());
            ps.setString(6, product.getImage());
            ps.setInt(7,quantity);


            ps.executeUpdate();

            System.out.println("Product : "+"*"+product.getNameProduct()+"*"+ "added succesfuly to shopping cart");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addToWishList(WishList product, int quantity) {
        try {
            String req = "INSERT INTO `ShoppingCart`(`idUser`,`idProduct`,`nameProduct`,`PriceProduct`,`productDescription`,`image`,`quantity`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, 1);
            ps.setInt(2, product.getIdProduct());
            ps.setString(3, product.getNameProduct());
            ps.setFloat(   4, product.getPriceProduct());
            ps.setString(5, product.getProductDescription());
            ps.setString(6, product.getImage());
            ps.setInt(7,quantity);


            ps.executeUpdate();

            System.out.println("Product : "+"*"+product.getNameProduct()+"*"+ "added succesfuly to shopping cart");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void remove(int idP) {
        try {
            String req ="DELETE FROM `ShoppingCart` WHERE idProduct = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,idP);
            ps.executeUpdate();
            System.out.println("Product removed");
        }catch (SQLException exp){
            exp.printStackTrace();
        }
    }


    public float calculateTotal() {
        List<ShoppingCartModel> productsSelected = fetchProducts() ;
        float total = 0.0f;
        for (ShoppingCartModel product : productsSelected) {
            total += product.getPriceProduct()*product.getQuantity();
        }
        return total;
    }


}
