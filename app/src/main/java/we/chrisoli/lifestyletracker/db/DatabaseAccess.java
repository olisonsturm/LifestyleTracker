package we.chrisoli.lifestyletracker.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import we.chrisoli.lifestyletracker.model.User;

public class DatabaseAccess {

    private final String databaseUserName = "databaseUser.db";
    private SQLiteDatabase databaseUser;
    private final String databaseWaterName = "databaseWater.db";
    private SQLiteDatabase databaseWater;
    private final String databasePeeName = "databasePee.db";
    private SQLiteDatabase databasePee;
    private final String databaseShitName = "databaseShit.db";
    private SQLiteDatabase databaseShit;

    public DatabaseAccess(Context context, int MODE_PRIVATE) {
        SQLiteDatabase databaseUser = context.openOrCreateDatabase(databaseUserName, MODE_PRIVATE, null);
        SQLiteDatabase databaseWater = context.openOrCreateDatabase(databaseWaterName, MODE_PRIVATE, null);
        SQLiteDatabase databasePee = context.openOrCreateDatabase(databasePeeName, MODE_PRIVATE, null);
        SQLiteDatabase databaseShit = context.openOrCreateDatabase(databaseShitName, MODE_PRIVATE, null);
    }

    public void createDatabases() {
        // alle Datenbanken lokal erstellen
        databaseUser.execSQL("CREATE TABLE user(uid INT, username TEXT, password TEXT)");
        databaseWater.execSQL("CREATE TABLE water(wid INT, date DATE, amount INT, uid INT)");
        databasePee.execSQL("CREATE TABLE pee(pid INT, date DATE, amount INT, uid INT)");
        databaseShit.execSQL("CREATE TABLE shit(sid INT, date DATE, amount INT, uid INT)");

        databaseUser.close();
        databaseWater.close();
        databasePee.close();
        databaseShit.close();
    }

    public User getUser(int uid) {
        User user = new User();
        // bekommen des User-Models
        Cursor cursorUser = databaseUser.rawQuery("SELECT * FROM user WHERE uid = '" + uid + "'", null);
        cursorUser.moveToFirst();

        if (cursorUser.getCount() > 0) {
            user.setUid(cursorUser.getString(0)); // pk
            user.setFirstname(cursorUser.getString(1));
            user.setLastname(cursorUser.getString(2));
        }

        return user;
    }

    public void setUser(User user) {

    }
}
