package user.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Models.Promo;
import user.Models.user;
import user.Services.PromoService;
import user.Services.userService;

import java.util.List;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {

   /*     // Create an instance of userService
        userService us = new userService();

        // Test addUser2
        us.add2(new user("maryem", "zribi", "pwd", "maryem@mail.com"));

        user us2 = new user("khano", "houyem", "souleimen", "soltana@mail.com");
        us.add2(us2);

        // Test showUsers
        List<user> userList = us.show();
        for (user user : userList) {
            System.out.println(user);
        }

        // Test updateUser
        us.update(new user("queen","slay","purr","yassmama@gmail.com"), "soltana");

        //Test delete
        int userIdToDelete = 30;
        do {
            userIdToDelete ++;
            us.delete(userIdToDelete);
        }
        while (userIdToDelete < 80);

        // Test searchUsers
        System.out.println("\nSearch Results for 'maryem':");
        List<user> searchResults = us.search("maryem");
        for (user user : searchResults) {
            System.out.println(user);
        }

        // Test filterUsersByName
        System.out.println("\nFilter Results for 'khano':");
        List<user> filterResults = us.filterByName("khano");
        for (user user : filterResults) {
            System.out.println(user);
        }

        ///////////////////////////// TEST L CODE PROMO ////////////////////////////
        PromoService ps = new PromoService();
        // Test add
        Promo p1 = new Promo(70, java.sql.Date.valueOf("2024-02-04"), true, 83);
        Promo p2 = new Promo(55, java.sql.Date.valueOf("2024-05-05"), true, 84);
        ps.add2(p1);
        ps.add(p2);

        //Test show
        List<Promo> promoList = ps.show();
        for (Promo promo : promoList) {
            System.out.println(promo);
        }
        //Test delete
       ps.delete(18);

        //Test update
        ps.update2(p1,java.sql.Date.valueOf("2024-03-26"));*/


    }
}
