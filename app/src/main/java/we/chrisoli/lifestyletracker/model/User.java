package we.chrisoli.lifestyletracker.model;


import java.sql.Date;

import we.chrisoli.lifestyletracker.db.DataAccess;

public class User {

    private static User user;

    private int uid; // pk
    private String firstname;
    private String lastname;

    public static synchronized User getInstance() {
        DataAccess dataAccess = new DataAccess();
        if (user == null)
            user = new User();
        return user;
    }

    public int getUid() {
        return uid;
    }

    public int setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
