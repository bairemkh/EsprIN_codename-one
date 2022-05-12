package Modules;

import Utils.Enums.Roles;
import Utils.Enums.State;

import java.util.Date;

public class Extern extends User {
    private String entrepriseName;
    private String adresse;

    public Extern() {
super();
    }

    public Extern(long cinUser, String email, String passwd, String imgUrl, Roles role, Date createdAt, State state, String entrepriseName, String adresse) {
        super(cinUser, email, passwd, imgUrl, role, createdAt, state);
        this.entrepriseName = entrepriseName;
        this.adresse = adresse;
    }
    /* public Extern(long cinUser, String email, String passwd, imgUrl, role) {
        super(cinUser, email, passwd, imgUrl, role);
        this.adresse = adresse;
        this.entrepriseName = entrepriseName;
    }*/

    public String getEntrepriseName() {
        return entrepriseName;
    }

    public void setEntrepriseName(String entrepriseName) {
        this.entrepriseName = entrepriseName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
