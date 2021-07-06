package we.chrisoli.lifestyletracker.model;

public class Shit implements Type {

    String sid; // pk
    int amount;
    String date;
    String uid; // fk

    public Shit() {

    }

    public Shit(String sid, int amount, String date, String uid) {
        this.sid = sid;
        this.amount = amount;
        this.date = date;
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int getType() {
        return Type.TYPE_SHIT;
    }
}
