package tn.PilatePulse.services;

import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.ShoppingCartModel;
import tn.PilatePulse.model.WishList;
import tn.PilatePulse.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishListService {

    Connection cnx = MaConnexion.getInstance().getCnx();


    public List<WishList> fetchProducts (){
        List<WishList> productselected = new ArrayList<>();
        try{
            String req = "SELECT * FROM wishList";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                WishList p = new WishList();
                p.setIdProduct(rs.getInt(1));
                p.setNameProduct(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setProductDescription(rs.getString(4));
                p.setPriceProduct(rs.getFloat(5));


                productselected.add(p);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return productselected;
    }

    public void add(Product product) {
        try {
            String req = "INSERT INTO `wishlist`(`idProduct`,`nameProduct`,`PriceProduct`,`productDescription`,`image`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, product.getIdProduct());
            ps.setString(2, product.getNameProduct());
            ps.setFloat(   3, product.getPriceProduct());
            ps.setString(4, product.getProductDescription());
            ps.setString(5, product.getImage());


            ps.executeUpdate();

            System.out.println("Product : "+"*"+product.getNameProduct()+"*"+ "added succesfuly to shopping cart");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void remove(int idP) {
        try {
            String req ="DELETE FROM `wishlist` WHERE idProduct = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,idP);
            ps.executeUpdate();
            System.out.println("Product removed");
        }catch (SQLException exp){
            exp.printStackTrace();
        }
    }

}
