package tn.PilatePulse.services;

import tn.PilatePulse.interfaces.InterfaceCRUD;
import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements InterfaceCRUD<Category> {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void add(Category category) {

        try {
            String req = "INSERT INTO `category`(`idCategory`, `nameCat`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, category.getIdCategory());
            ps.setString(2, category.getNameCategory());

            ps.executeUpdate();

            System.out.println("Category added succesfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List fetchCategory(){

        List<Category> categories = new ArrayList<>();
        try{
            String req = "SELECT * FROM category";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Category cat = new Category();
                cat.setIdCategory(rs.getInt(1));
                cat.setNameCategory(rs.getString(2));

                categories.add(cat);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return categories;
    }

    @Override
    public void update(Category category) {

        try {
            String req = "UPDATE `category` SET `nameCat`=? WHERE `idCategory`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, category.getNameCategory());

            System.out.println("Category * "+category.getNameCategory()+" * added successfuly");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public void remove(int idc){

        try {
            String req ="DELETE FROM `category` WHERE idCategory = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,idc);
            ps.executeUpdate();
            System.out.println("Category removed");
        }catch (SQLException exp){
            exp.printStackTrace();
        }
    }

    public List<Category> rechercheParId(int idc){
        List<Category> categories = new ArrayList<>();
        try {

            String req = "SELECT * FROM category WHERE IdCategory LIKE CONCAT(?, '%')";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idc);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setIdCategory(rs.getInt(1));
                c.setNameCategory(rs.getString(2));

                categories.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (categories.isEmpty()){
            System.out.println("product unvailable");
        }
        return categories ;
    }


    public List<Category> rechercheParNom(String nomCat){
        List<Category> categories = new ArrayList<>();
        try {

            String req = "SELECT * FROM category WHERE nameCat LIKE ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, nomCat);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setIdCategory(rs.getInt(1));
                c.setNameCategory(rs.getString(2));

                categories.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (categories.isEmpty()){
            System.out.println("product unvailable");
        }
        return categories ;
    }


}
