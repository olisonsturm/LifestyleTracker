package we.chrisoli.lifestyletracker.model;



import java.sql.Date;

import we.chrisoli.lifestyletracker.db.DataAccess;

public class User {

    private static User user;

    private String uid; // pk
    private String firstname;
    private String lastname;
    private Date birth;

    public static synchronized User getInstance() {
        DataAccess dataAccess = new DataAccess();
        if (user == null)
            user = dataAccess.getUser("1");
        return user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
