package Modules;

import Utils.Enums.Domaine;
import Utils.Enums.Roles;

import java.util.Date;

public class Student extends Espritien{
    private String classe;
    private Domaine domaine;

    public Student(long cinUser, String email, String passwd, String imgUrl, Roles role, String firstName, String lastName, String classe, Domaine domaine){
        super(cinUser,email,passwd,imgUrl,role,firstName,lastName);
        this.classe=classe;
        this.domaine=domaine;
    }

    public Student(){
        super();
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        return super.toString()+ " " +
                "classe=" + classe +
                ", domaine=" + domaine +
                "}\n";
    }
}
