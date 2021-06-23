package we.chrisoli.lifestyletracker.model;

public class User {

    String uid; // pk
    String firstname;
    String lastname;
    String birth;

    public User() {

    }

    public User(String uid, String firstname, String lastname, String birth) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth = birth;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
