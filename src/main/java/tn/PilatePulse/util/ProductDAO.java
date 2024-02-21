package tn.PilatePulse.util;

import tn.PilatePulse.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // other methods here

   /* public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double priceProduct = resultSet.getDouble("priceProduct");
                int idCategory = resultSet.getInt("idCategory");

                Category category = new Category(idCategory, ""); // you might need to fetch the category name from the database

                Product product = new Product(idProduct, nameProduct, priceProduct, category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }*/
}