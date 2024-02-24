package user.Models;
import java.sql.Date;
import java.time.LocalDate;


public class Promo {

    private int code;
    private float pourcentage;
    private Date validite;
    private boolean isActive;
    private int id;

    public Promo() {
    }


    public Promo(float pourcentage, Date validite, boolean isActive,int id) {
        this.pourcentage = pourcentage;
        this.validite = validite;
        this.isActive = isActive;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getValidite() {
        return validite;
    }

    public void setValidite(Date validite) {
        this.validite = validite;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Promo{" +
                "id=" + id +
                ", code=" + code +
                ", pourcentage=" + pourcentage +
                ", validity=" + validite +
                ", isActive=" + isActive +
                '}';
    }


}

