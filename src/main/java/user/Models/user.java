package user.Models;

public class user {
    private int id;
    private String nom;
    private String Prenom;
    private String mdp;
    private String mail;
    private String role;
    private int numTel;



    public user(){
    }
    public user( String nom, String prenom, String mdp, String mail, String role,int numTel) {

        this.nom = nom;
        this.Prenom = prenom;
        this.mdp = mdp;
        this.mail = mail;
        this.role = role;
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

  /*  @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", mdp='" + mdp + '\'' +
                ", mail='" + mail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }*/
    @Override
    public String toString() {
        return "user{" +
                " nom='" + nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
