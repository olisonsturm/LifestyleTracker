package we.chrisoli.lifestyletracker.model;

public class Water implements Type {

    String wid; // pk
    String amount;
    String date;
    String uid; // fk

    public Water() {
    }

    public Water(String wid, String amount, String date, String uid) {
        this.wid = wid;
        this.amount = amount;
        this.date = date;
        this.uid = uid;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
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
        return Type.TYPE_WATER;
    }
}
