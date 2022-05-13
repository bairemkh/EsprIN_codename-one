package Utils;

import Modules.User;
import Services.UserServices;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;

import java.io.IOException;

public class SessionManager {
    private static SessionManager instance;
    private Database db;

    public SessionManager() {
        try {

            if (!Database.exists("esprin")) {
                this.db = Database.openOrCreate("esprin");
                db.execute("create table if not exists session(" +
                        "userid TEXT);");
            }
            else{
                this.db = Database.openOrCreate("esprin");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static SessionManager getInstance() {
        if (instance == null)
            instance = new SessionManager();
        return instance;
    }

    public Database getDb() {
        return db;
    }

    public String getSession(){
        try {
            Cursor c= db.executeQuery("select userid from session;");
            while(c.next()){
                Row row=c.getRow();
                return row.getString(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public void insertSession(String email){
        try {
            db.execute("insert into session(userid) values('"+email+"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteSession(){
        try {
            db.execute("Delete from session");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public User getCurrentUser(){
        UserServices userServices=UserServices.getInstance();

        return userServices.getUserByEmail(this.getSession());
    }
}
