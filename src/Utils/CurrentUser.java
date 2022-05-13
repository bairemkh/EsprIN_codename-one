package Utils;

import Modules.User;

public class CurrentUser {
    private static CurrentUser instance;
    private User currentUser;
    private String loginToken;

    public User getCurrentUser() {
        return currentUser;
    }

    private CurrentUser(User currentUser,String token) {
        this.currentUser = currentUser;
        this.loginToken=token;
    }


    public static void clearInstance() {
        instance = null;
    }
    public static CurrentUser getInstance() {
        return instance;
    }
    public static void setInstance(User user,String loginToken) {
        instance=new CurrentUser(user,loginToken);
    }
}
