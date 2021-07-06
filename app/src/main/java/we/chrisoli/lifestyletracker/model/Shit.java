package we.chrisoli.lifestyletracker.model;

public class Shit implements Type {

    int sid; // pk
    String amount;
    String date;
    String uid; // fk

    public Shit() {

    }

    public Shit(int sid, String amount, String date, String uid) {
        this.sid = sid;
        this.amount = amount;
        this.date = date;
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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
