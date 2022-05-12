package Modules;

import Utils.Enums.Roles;
import Utils.Enums.State;
import Utils.Enums.TypeClub;

import java.util.Date;

public class Club extends Espritien{
    private TypeClub type;

    public Club() {

    }

    public Club(long cinUser, String email, String passwd, String imgUrl, Roles role, Date createdAt, State state, String firstName, String lastName, TypeClub type) {
        super(cinUser, email, passwd, imgUrl, role, createdAt, state, firstName, lastName);
        this.type = type;
    }

    public TypeClub getTypeClub() {
        return type;
    }

    public void setType(TypeClub type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString()+ "type= " + type +
                '\n';
    }
}
