package tn.PilatePulse.services;

import tn.PilatePulse.model.Product;
import tn.PilatePulse.util.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShoppingCartService {

    Connection cnx = MaConnexion.getInstance().getCnx();

    public void add(Product product) {
        try {
            String req = "INSERT INTO `ShoppingCart`(`idProduct`,`nameProduct`,`PriceProduct`,`productDescription`,`image`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, product.getIdProduct());
            ps.setString(2, product.getNameProduct());
            ps.setFloat(   3, product.getPriceProduct());
            ps.setString(4, product.getProductDescription());
            ps.setString(3, product.getImage());


            ps.executeUpdate();

            System.out.println("Product : "+"*"+product.getNameProduct()+"*"+ "added succesfuly to shopping cart");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
