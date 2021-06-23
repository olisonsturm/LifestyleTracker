package we.chrisoli.lifestyletracker.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import we.chrisoli.lifestyletracker.model.Pee;
import we.chrisoli.lifestyletracker.model.Shit;
import we.chrisoli.lifestyletracker.model.User;
import we.chrisoli.lifestyletracker.model.Water;


public class DataAccess {

    private static Connection con = null;

    public DataAccess() {
        MySQLConnection mySqlCon = new MySQLConnection();
        con = mySqlCon.startConnection();
    }
    // --------------------------------------------------------------
    // Wasser
    public Water getWater(User user) {
        Water water = new Water();

        // Anfrage-Statement erzeugen
        Statement query;
        try {
            query = con.createStatement();

            // Ergebnistabelle erzeugen und abholen
            String sql = "Select * from water where uid ='" + user.getUid() + "'";
            ResultSet result = query.executeQuery(sql);

            // Ergebnissätze durchlaufen
            while (result.next()) {
                water.setWid(result.getString("wid")); // pk
                water.setAmount(result.getString("amount"));
                water.setDate(result.getString("date"));
                water.setUid(result.getString("uid")); // fk
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return water;
    }
    public boolean setWater(User user, Water water) {
        try {
            // Insert-Statement erzeugen (Fragezeichen werden später ersetzt).
            String sql = "INSERT INTO water(wid, amount, date, uid) " +
                    "VALUES(null, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // Index 0 = wid (pk)
            // Fragezeichen durch Parameter ersetzen
            preparedStatement.setString(1, water.getAmount());
            preparedStatement.setString(2, water.getDate());
            preparedStatement.setString(3, user.getUid()); // fk

            // SQL ausführen
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    // --------------------------------------------------------------
    // Pee
    public Pee getPee(User user) {
        Pee pee = new Pee();

        // Anfrage-Statement erzeugen
        Statement query;
        try {
            query = con.createStatement();

            // Ergebnistabelle erzeugen und abholen
            String sql = "Select * from pee where uid ='" + user.getUid() + "'";
            ResultSet result = query.executeQuery(sql);

            // Ergebnissätze durchlaufen
            while (result.next()) {
                pee.setPid(result.getString("pid")); // pk
                pee.setAmount(result.getString("amount"));
                pee.setDate(result.getString("date"));
                pee.setUid(result.getString("uid")); // fk
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pee;
    }
    public boolean setPee(User user, Pee pee) {
        try {
            // Insert-Statement erzeugen (Fragezeichen werden später ersetzt).
            String sql = "INSERT INTO pee(pid, amount, date, uid) " +
                    "VALUES(null, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // Index 0 = wid (pk)
            // Fragezeichen durch Parameter ersetzen
            preparedStatement.setString(1, pee.getAmount());
            preparedStatement.setString(2, pee.getDate());
            preparedStatement.setString(3, user.getUid()); // fk

            // SQL ausführen
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    // --------------------------------------------------------------
    // Shit
    public Shit getShit(User user) {
        Shit shit = new Shit();

        // Anfrage-Statement erzeugen
        Statement query;
        try {
            query = con.createStatement();

            // Ergebnistabelle erzeugen und abholen
            String sql = "Select * from shit where uid ='" + user.getUid() + "'";
            ResultSet result = query.executeQuery(sql);

            // Ergebnissätze durchlaufen
            while (result.next()) {
                shit.setSid(result.getString("sid")); // pk
                shit.setAmount(result.getString("amount"));
                shit.setDate(result.getString("date"));
                shit.setUid(result.getString("uid")); // fk
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shit;
    }
    public boolean setShit(User user, Shit shit) {
        try {
            // Insert-Statement erzeugen (Fragezeichen werden später ersetzt).
            String sql = "INSERT INTO shit(sid, amount, date, uid) " +
                    "VALUES(null, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // Index 0 = wid (pk)
            // Fragezeichen durch Parameter ersetzen
            preparedStatement.setString(1, shit.getAmount());
            preparedStatement.setString(2, shit.getDate());
            preparedStatement.setString(3, user.getUid()); // fk

            // SQL ausführen
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}