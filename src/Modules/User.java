package Modules;


import Utils.Enums.Roles;
import Utils.Enums.State;

import java.util.Date;

public class User {
    protected long cinUser;
    protected String email;
    protected String passwd;
    protected String imgUrl;
    protected Roles role;
    protected Date createdAt;
    protected State state;

    public User() {

    }

    public long getCinUser() {
        return cinUser;
    }

    public void setCinUser(long cinUser) {
        this.cinUser = cinUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Roles getRole() {
        return role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    protected User(long cinUser, String email, String passwd, String imgUrl, Roles role) {
        this.cinUser = cinUser;
        this.email = email;
        this.passwd = passwd;
        this.imgUrl = imgUrl;
        this.role = role;
    }

    public User(long cinUser, String email, String passwd, String imgUrl, Roles role, Date createdAt, State state) {
        this.cinUser = cinUser;
        this.email = email;
        this.passwd = passwd;
        this.imgUrl = imgUrl;
        this.role = role;
        this.createdAt = createdAt;
        this.state = state;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "cinUser=" + cinUser +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", role=" + role +
                '\n';
    }
}
