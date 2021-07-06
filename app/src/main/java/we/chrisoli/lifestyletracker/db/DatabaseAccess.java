package we.chrisoli.lifestyletracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import we.chrisoli.lifestyletracker.model.Pee;
import we.chrisoli.lifestyletracker.model.Shit;
import we.chrisoli.lifestyletracker.model.User;
import we.chrisoli.lifestyletracker.model.Water;

public class DatabaseAccess {

    private final String databaseUserName = "databaseUser.db";
    private static SQLiteDatabase databaseUser = null;
    private final String databaseWaterName = "databaseWater.db";
    private static SQLiteDatabase databaseWater = null;
    private final String databasePeeName = "databasePee.db";
    private static SQLiteDatabase databasePee = null;
    private final String databaseShitName = "databaseShit.db";
    private static SQLiteDatabase databaseShit = null;

    public DatabaseAccess(Context context, int MODE_PRIVATE) {
        this.databaseUser = context.openOrCreateDatabase(databaseUserName, MODE_PRIVATE, null);
        this.databaseWater = context.openOrCreateDatabase(databaseWaterName, MODE_PRIVATE, null);
        this.databasePee = context.openOrCreateDatabase(databasePeeName, MODE_PRIVATE, null);
        this.databaseShit = context.openOrCreateDatabase(databaseShitName, MODE_PRIVATE, null);
    }

    public void createDatabases() {
        // alle Datenbanken lokal erstellen
        databaseUser.execSQL("CREATE TABLE user(uid INT, username TEXT, password TEXT)");
        databaseWater.execSQL("CREATE TABLE water(wid INT, date DATE, amount INT, uid INT)");
        databasePee.execSQL("CREATE TABLE pee(pid INT, date DATE, amount INT, uid INT)");
        databaseShit.execSQL("CREATE TABLE shit(sid INT, date DATE, amount INT, uid INT)");
    }

    public User getUser(int uid) {
        User user = new User();
        // bekommen des User-Models
        Cursor cursorUser = databaseUser.rawQuery("SELECT * FROM user WHERE uid = '" + uid + "'", null);
        cursorUser.moveToFirst();

        if (cursorUser.getCount() > 0) {
            user.setUid(cursorUser.getInt(0)); // pk
            user.setFirstname(cursorUser.getString(1));
            user.setLastname(cursorUser.getString(2));
        }
        cursorUser.close();
        return user;
    }

    public Water getWater(User user) {
        Water water = new Water();
        // bekommen des User-Models
        Cursor cursorUser = databaseUser.rawQuery("SELECT * FROM water WHERE uid = '" + user.getUid() + "'", null);
        cursorUser.moveToFirst();

        if (cursorUser.getCount() > 0) {
            water.setWid(cursorUser.getInt(0)); // pk
            water.setAmount(cursorUser.getString(1));
            water.setDate(cursorUser.getString(2));
            water.setUid(cursorUser.getString(3)); //fk
        }
        cursorUser.close();
        return water;
    }

    public Pee getPee(User user) {
        Pee pee = new Pee();
        // bekommen des User-Models
        Cursor cursorUser = databaseUser.rawQuery("SELECT * FROM pee WHERE uid = '" + user.getUid() + "'", null);
        cursorUser.moveToFirst();

        if (cursorUser.getCount() > 0) {
            pee.setPid(cursorUser.getInt(0)); // pk
            pee.setAmount(cursorUser.getString(1));
            pee.setDate(cursorUser.getString(2));
            pee.setUid(cursorUser.getString(3)); //fk
        }
        cursorUser.close();
        return pee;
    }

    public Shit getShit(User user) {
        Shit shit = new Shit();
        // bekommen des User-Models
        Cursor cursorUser = databaseUser.rawQuery("SELECT * FROM shit WHERE uid = '" + user.getUid() + "'", null);
        cursorUser.moveToFirst();

        if (cursorUser.getCount() > 0) {
            shit.setSid(cursorUser.getInt(0)); // pk
            shit.setAmount(cursorUser.getString(1));
            shit.setDate(cursorUser.getString(2));
            shit.setUid(cursorUser.getString(3)); //fk
        }
        cursorUser.close();
        return shit;
    }

    public void setUser(String firstname, String lastname) {
//        ContentValues cv = new ContentValues();
//        cv.put("uid", 0);
//        cv.put("firstname", firstname);
//        cv.put("lastname", lastname);
//        databaseUser.insert(databaseUserName, null, cv);
        databaseUser.execSQL("INSERT INTO " + databaseUserName +" (uid, firstname, lastname) "
                + " VALUES (" + 0 + ", '" + firstname + "', '" + lastname + "');");
    }

    public boolean checkLogIn(String firstname, String lastname) {
        boolean okay = false;

        Cursor cursorUser = databaseUser.rawQuery("SELECT lastname FROM user WHERE firstname = '" + firstname + "'", null);
        cursorUser.moveToFirst();

        if (cursorUser.getCount() > 0) {
            if (cursorUser.getString(0).equals(lastname)) {
                okay = true;
            }
        }
        cursorUser.close();
        return okay;
    }
}
