package we.chrisoli.lifestyletracker.model;

public class Pee implements Type {

    String pid; // pk
    int amount;
    String date;
    String uid; // fk

    public Pee() {

    }

    public Pee(String pid, int amount, String date, String uid) {
        this.pid = pid;
        this.amount = amount;
        this.date = date;
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
        return Type.TYPE_PEE;
    }
}
