package user.Utils;
import user.Models.user;

public class UserSession {
    private static int id;
    private static String nom;
    private static String prenom;
    private static String mail;
    private static String role;
    private static String mdp;
    private static int numTel;


    public static void setSession(user u){
        id = u.getId();
        nom = u.getNom();
        prenom = u.getPrenom();
        mail = u.getMail();
        mdp = u.getMdp();
        role = u.getRole();
        numTel = u.getNumTel();

    }
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserSession.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        UserSession.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        prenom = prenom;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        UserSession.mail = mail;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserSession.role = role;
    }
    public static int getNumTel() {return numTel;}

    public static void setNumTel(int numTel) {
        UserSession.numTel = numTel;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        UserSession.mdp = mdp;
    }
}
