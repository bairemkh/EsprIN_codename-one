package Services;

import Modules.*;
import Utils.Enums.AdminDepartments;
import Utils.Enums.Domaine;
import Utils.Enums.Roles;
import Utils.Enums.TypeClub;
import Utils.SessionManager;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Utils.UsefulMethodes.convertStringToDate;

public class UserServices {
    private static UserServices instance;
    private final String URL = "http://127.0.0.1:8000/api/";
    private ConnectionRequest req;
    public ArrayList<User> userArrayList;
    public boolean resultOK;
    public int code;
    private UserServices() {
        req = new ConnectionRequest();
    }

    public static UserServices getInstance() {
        if (instance == null)
            instance = new UserServices();
        return instance;
    }

    public ArrayList<User> getUsers() {
        req = new ConnectionRequest();
        String url = URL + "getStudents";
        //System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userArrayList = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return userArrayList;
    }

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            userArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson =
                    j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                u.setRole(Roles.valueOf(obj.get("role").toString()));
                switch (u.getRole()) {
                    case Admin:
                        Admin admin = new Admin();
                        float id = Float.parseFloat(obj.get("cinuser").toString());
                        admin.setCinUser((long) id);
                        admin.setImgUrl(obj.get("imgurl").toString());
                        admin.setEmail(obj.get("email").toString());
                        admin.setCreatedAt(convertStringToDate(obj.get("createdat").toString()));
                        admin.setRole(Roles.valueOf(obj.get("role").toString()));
                        admin.setFirstName(obj.get("firstname").toString());
                        admin.setLastName(obj.get("lastname").toString());
                        admin.setDepartment(AdminDepartments.valueOf(obj.get("departement").toString()));
                        userArrayList.add(admin);
                        break;
                    case Club:
                        Club club = new Club();
                        club.setCinUser((long) Float.parseFloat(obj.get("cinuser").toString()));
                        club.setImgUrl(obj.get("imgurl").toString());
                        club.setEmail(obj.get("email").toString());
                        club.setCreatedAt(convertStringToDate(obj.get("createdat").toString()));
                        club.setRole(Roles.valueOf(obj.get("role").toString()));
                        club.setFirstName(obj.get("firstname").toString());
                        club.setLastName(obj.get("lastname").toString());
                        club.setType(TypeClub.valueOf(obj.get("typeclub").toString()));
                        userArrayList.add(club);
                        break;
                    case Professor:
                        Professor professor = new Professor();
                        professor.setCinUser((long) Float.parseFloat(obj.get("cinuser").toString()));
                        professor.setImgUrl(obj.get("imgurl").toString());
                        professor.setEmail(obj.get("email").toString());
                        professor.setCreatedAt(convertStringToDate(obj.get("createdat").toString()));
                        professor.setRole(Roles.valueOf(obj.get("role").toString()));
                        professor.setFirstName(obj.get("firstname").toString());
                        professor.setLastName(obj.get("lastname").toString());
                        professor.setDomaine(Domaine.valueOf(obj.get("domaine").toString().replace(' ', '_')));
                        userArrayList.add(professor);
                        break;
                    case Student:
                        Student student = new Student();
                        student.setCinUser((long) Float.parseFloat(obj.get("cinuser").toString()));
                        student.setImgUrl(obj.get("imgurl").toString());
                        student.setEmail(obj.get("email").toString());
                        student.setCreatedAt(convertStringToDate(obj.get("createdat").toString()));
                        student.setRole(Roles.valueOf(obj.get("role").toString()));
                        student.setFirstName(obj.get("firstname").toString());
                        student.setLastName(obj.get("lastname").toString());
                        student.setDomaine(Domaine.valueOf(obj.get("domaine").toString().replace(' ', '_')));
                        student.setClasse(obj.get("class").toString());
                        userArrayList.add(student);
                        break;
                    case Extern:
                        Extern extern = new Extern();
                        extern.setCinUser((long) Float.parseFloat(obj.get("cinuser").toString()));
                        extern.setImgUrl(obj.get("imgurl").toString());
                        extern.setEmail(obj.get("email").toString());
                        extern.setCreatedAt(convertStringToDate(obj.get("createdat").toString()));
                        extern.setRole(Roles.valueOf(obj.get("role").toString()));
                        extern.setEntrepriseName(obj.get("entreprisename").toString());
                        extern.setAdresse(obj.get("localisation").toString());
                        userArrayList.add(extern);
                        break;
                }

            }


        } catch (IOException ex) {

        }
        return userArrayList;
    }

    public boolean addUser(User u) {
        String url = URL + "createNewAccount";
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        req.setReadResponseForErrors(true);
        req.addArgument("cinuser", String.valueOf(u.getCinUser()));
        req.addArgument("email", u.getEmail());
        req.addArgument("passwd", u.getPasswd());
        if (u.getImgUrl() != null) {
            req.addArgument("imgurl", u.getImgUrl());
        }
        req.addArgument("role", u.getRole().name());
        Espritien espritien = (Espritien) u;
        req.addArgument("firstname", espritien.getFirstName());
        req.addArgument("lastname", espritien.getLastName());


        switch (u.getRole()) {
            case Student:
                Student student = (Student) u;
                req.addArgument("class", student.getClasse());
                req.addArgument("domaine", student.getDomaine().name());
                break;
            case Admin:
                Admin admin = (Admin) u;
                req.addArgument("departement", admin.getDepartment().name());
                break;
            case Professor:
                Professor professor = (Professor) u;
                req.addArgument("domaine", professor.getDomaine().name());
                break;
            case Club:
                Club club = (Club) u;
                req.addArgument("typeclub", club.getTypeClub().name());
                break;
            case Extern:
                Extern extern = (Extern) u;
                req.addArgument("localisation", extern.getAdresse());
                req.addArgument("entreprisename", extern.getEntrepriseName());
                break;
        }

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(req.getRequestBody());
        System.out.println(req.getUrl());
        System.out.println(req.getHttpMethod());
        JSONParser parser = new JSONParser();
        Map map = null;
        try {
            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return resultOK;
    }

    public Club getClubByEvent(Event event) {

        ArrayList<User> users = this.getUsers();
        for (User u : users) {
            //if(u.getCinUser())
        }
        return null;
    }

    public boolean login(String email, String password) {
        String url = URL + "loginCheck";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("userMail", email);
        req.addArgument("userPasswd", password);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                code=checkLogin(req.getResponseCode(), email);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return code==200;
    }

    public int checkLogin(int code, String email) {
        if (code == 200) {
            SessionManager sessionManager = SessionManager.getInstance();
            sessionManager.insertSession(email);
        }
        return code;
    }

    public User getUserByEmail(String email) {
        ArrayList<User> users = this.getUsers();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

}
