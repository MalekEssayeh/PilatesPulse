package tn.PilatePulse.test;

import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.CategoryService;
import tn.PilatePulse.services.ProductService;
import tn.PilatePulse.util.MaConnexion;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){

        Connection cnx = MaConnexion.getInstance().getCnx();


        Category c1 = new Category(1,"Vêtements");
        Category c2 = new Category(2,"accessoires");

        CategoryService cs = new CategoryService();
        //cs.add(c2);

       // Product p1 = new Product("T-shirt",45,10,1 );
       // Product p2 = new Product("PANTALON JOGGING",65,15,1);
       // Product p3 = new Product("LEGGING",60,20,1);
        //Product p4 = new Product("PLANCHE D'ÉQUILIBRE",150,5,2);
       // ProductService ps = new ProductService();

        //ps.add(p1);
        //ps.add(p2);
       // ps.add(p3);
        //ps.add(p4);
        //ps.remove(17);

        //System.out.println(ps.fetchProduct());
        //System.out.println(ps.rechercheParId(10));
       // System.out.println(ps.rechercheParNom("T-shirt"));
        //System.out.println(ps.rechercheParNom("PANTALON"));

        //System.out.println(ps.filterProductsByCategoryName("vetement"));

       // System.out.println(ps.filterProductsByPriceRange(10,20));

    }
}
