package we.chrisoli.lifestyletracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import we.chrisoli.lifestyletracker.model.Pee;
import we.chrisoli.lifestyletracker.model.Shit;
import we.chrisoli.lifestyletracker.model.Type;
import we.chrisoli.lifestyletracker.model.User;
import we.chrisoli.lifestyletracker.model.Water;

public class DatabaseAccess extends SQLiteOpenHelper {

    private static final String databaseName = "databaseLifestyleTracker.db";

    public DatabaseAccess(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // alle Datenbanken lokal erstellen
        db.execSQL("CREATE TABLE user(uid INT, username TEXT, lastname TEXT)");
        db.execSQL("CREATE TABLE water(wid TEXT, date DATE, amount INT, uid INT)");
        db.execSQL("CREATE TABLE pee(pid TEXT, date DATE, amount INT, uid INT)");
        db.execSQL("CREATE TABLE shit(sid TEXT, date DATE, amount INT, uid INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //================< Region: Insert,Update,Delete >================
    public User getUser(int uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        User user = new User();
        // select
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE uid = '" + uid + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            user.setUid(cursor.getInt(0)); // pk
            user.setFirstname(cursor.getString(1));
            user.setLastname(cursor.getString(2));
        }
        cursor.close();
        return user;
    }

    public void setUser(String firstname, String lastname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uid", 0);
        cv.put("firstname", firstname);
        cv.put("lastname", lastname);
        db.insert("user", null, cv);
    }

    public Water getWater() {
        SQLiteDatabase db = this.getWritableDatabase();
        Water water = new Water();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd.MM.yyyy");
        // select
        Cursor cursor = db.rawQuery("SELECT * FROM water WHERE date = '" + formatter.format(new Date()) + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            water.setWid(cursor.getString(0)); // pk
            water.setDate(cursor.getString(1));
            water.setAmount(cursor.getInt(2));
            water.setUid(cursor.getString(3)); //fk
        }
        cursor.close();
        return water;
    }

    public void setWater(Water water) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("wid", "water");
        cv.put("amount", water.getAmount());
        cv.put("date", water.getDate());
        cv.put("uid", 0);
        long u = db.update("water", cv, "date=?", new String[]{water.getDate()});
        if (u == 0) {
            db.insertWithOnConflict("water", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public Pee getPee() {
        SQLiteDatabase db = this.getWritableDatabase();
        Pee pee = new Pee();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd.MM.yyyy");
        // select
        Cursor cursor = db.rawQuery("SELECT * FROM pee WHERE date = '" + formatter.format(new Date()) + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            pee.setPid(cursor.getString(0)); // pk
            pee.setDate(cursor.getString(1));
            pee.setAmount(cursor.getInt(2));
            pee.setUid(cursor.getString(3)); //fk
        }
        cursor.close();
        return pee;
    }

    public void setPee(Pee pee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("pid", "pee");
        cv.put("amount", pee.getAmount());
        cv.put("date", pee.getDate());
        cv.put("uid", 0);
        long u = db.update("pee", cv, "date=?", new String[]{pee.getDate()});
        if (u == 0) {
            db.insertWithOnConflict("pee", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public Shit getShit() {
        SQLiteDatabase db = this.getWritableDatabase();
        Shit shit = new Shit();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd.MM.yyyy");
        // select
        Cursor cursor = db.rawQuery("SELECT * FROM shit WHERE date = '" + formatter.format(new Date()) + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            shit.setSid(cursor.getString(0)); // pk
            shit.setDate(cursor.getString(1));
            shit.setAmount(cursor.getInt(2));
            shit.setUid(cursor.getString(3)); //fk
        }
        cursor.close();
        return shit;
    }

    public void setShit(Shit shit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sid", "shit");
        cv.put("amount", shit.getAmount());
        cv.put("date", shit.getDate());
        cv.put("uid", 0);
        long u = db.update("shit", cv, "date=?", new String[]{shit.getDate()});
        if (u == 0) {
            db.insertWithOnConflict("shit", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public ArrayList<String> getYesterday() {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd.MM.yyyy");
        // bekomme das datum von gestern
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        String dateYesterday = formatter.format(c.getTime());
        // select
        Cursor cursor1 = db.rawQuery("SELECT * FROM water WHERE date = '" + dateYesterday + "'", null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM pee WHERE date = '" + dateYesterday + "'", null);
        Cursor cursor3 = db.rawQuery("SELECT * FROM shit WHERE date = '" + dateYesterday + "'", null);
        // erstellen
        ArrayList<String> all = new ArrayList<String>();
        Water water = new Water();
        Pee pee = new Pee();
        Shit shit = new Shit();
        // iterate
        if (cursor1.getCount() > 0) {
            cursor1.moveToFirst();
            while (!cursor1.isAfterLast()) {
                all.add(cursor1.getString(0) + "    |    " + cursor1.getString(1) + "    |    " + cursor1.getInt(2));
                cursor1.moveToNext();
            }
        }
        if (cursor2.getCount() > 0) {
            cursor2.moveToFirst();
            while (!cursor2.isAfterLast()) {
                all.add(cursor2.getString(0) + "        |    " + cursor2.getString(1) + "    |    " + cursor2.getInt(2));
                cursor2.moveToNext();
            }
        }
        if (cursor3.getCount() > 0) {
            cursor3.moveToFirst();
            while (!cursor3.isAfterLast()) {
                all.add(cursor3.getString(0) + "        |    " + cursor3.getString(1) + "    |    " + cursor3.getInt(2));
                cursor3.moveToNext();
            }
        }
        cursor1.close();
        cursor2.close();
        cursor3.close();
        return all;
    }

    public ArrayList<String> getAllTime() {
        SQLiteDatabase db = this.getWritableDatabase();
        // select
        Cursor cursor1 = db.rawQuery("SELECT * FROM water", null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM pee", null);
        Cursor cursor3 = db.rawQuery("SELECT * FROM shit", null);
        // erstellen
        ArrayList<String> all = new ArrayList<String>();
        Water water = new Water();
        Pee pee = new Pee();
        Shit shit = new Shit();
        // iterate
        if (cursor1.getCount() > 0) {
            cursor1.moveToFirst();
            while (!cursor1.isAfterLast()) {
                all.add(cursor1.getString(0) + "    |    " + cursor1.getString(1) + "    |    " + cursor1.getInt(2));
                cursor1.moveToNext();
            }
        }
        if (cursor2.getCount() > 0) {
            cursor2.moveToFirst();
            while (!cursor2.isAfterLast()) {
                all.add(cursor2.getString(0) + "        |    " + cursor2.getString(1) + "    |    " + cursor2.getInt(2));
                cursor2.moveToNext();
            }
        }
        if (cursor3.getCount() > 0) {
            cursor3.moveToFirst();
            while (!cursor3.isAfterLast()) {
                all.add(cursor3.getString(0) + "        |    " + cursor3.getString(1) + "    |    " + cursor3.getInt(2));
                cursor3.moveToNext();
            }
        }
        cursor1.close();
        cursor2.close();
        cursor3.close();
        return all;
    }

}
