package we.chrisoli.lifestyletracker.db;

import com.BoardiesITSolutions.AndroidMySQLConnector.Connection;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.InvalidSQLPacketException;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.MySQLConnException;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.MySQLException;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.SQLColumnNotFoundException;
import com.BoardiesITSolutions.AndroidMySQLConnector.IConnectionInterface;
import com.BoardiesITSolutions.AndroidMySQLConnector.IResultInterface;
import com.BoardiesITSolutions.AndroidMySQLConnector.MySQLRow;
import com.BoardiesITSolutions.AndroidMySQLConnector.ResultSet;
import com.BoardiesITSolutions.AndroidMySQLConnector.Statement;

import java.io.IOException;

import we.chrisoli.lifestyletracker.model.User;


public class DataAccess {

    private Connection con = null;
    String host = "35.234.92.142";
    int port = 3306;
    String db = "freedbtech_lifestyletracker";
    String username = "freedbtech_chrisoli";
    String password = "tracker";

    public DataAccess() {
        startConnection();
    }

    public Connection startConnection() {
        con = new Connection(host, username, password, port, new IConnectionInterface() {
            @Override
            public void actionCompleted() {
                System.out.println("[MySQL] Connection started");
            }

            @Override
            public void handleInvalidSQLPacketException(InvalidSQLPacketException ex) {
                System.out.println("[MySQL] " + ex.getMessage());
            }

            @Override
            public void handleMySQLException(MySQLException ex) {
                System.out.println("[MySQL] " + ex.getMessage());
            }

            @Override
            public void handleIOException(IOException ex) {
                System.out.println("[MySQL] " + ex.getMessage());
            }

            @Override
            public void handleMySQLConnException(MySQLConnException ex) {
                System.out.println("[MySQL] " + ex.getMessage());
            }

            @Override
            public void handleException(Exception exception) {
                System.out.println("[MySQL] " + exception.getMessage());
            }
        });
        return con;
    }

    public void closeConnection() {
        con.close();
    }

    //------------------------------------------------------------------
    // User
    public User getUser(String firstname, String lastname) {
        User user = new User();

        // Anfrage-Statement erzeugen
        Statement statement = con.createStatement();

        // Ergebnistabelle erzeugen und abholen
        String sql = "Select * from user where firstname ='" + firstname + "' lastname ='" + lastname + "'";
        statement.executeQuery(sql, new IResultInterface() {
            @Override
            public void executionComplete(ResultSet resultSet) {
                // Ergebnissätze durchlaufen
                MySQLRow row;
                while ((row = resultSet.getNextRow()) != null) {
                    try {
                        user.setUid(row.getString("uid")); // pk
                        user.setFirstname(row.getString("firstname"));
                        user.setLastname(row.getString("lastname"));
                    } catch (SQLColumnNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void handleInvalidSQLPacketException(InvalidSQLPacketException ex) {

            }

            @Override
            public void handleMySQLException(MySQLException ex) {

            }

            @Override
            public void handleIOException(IOException ex) {

            }

            @Override
            public void handleMySQLConnException(MySQLConnException ex) {

            }

            @Override
            public void handleException(Exception ex) {

            }
        });
        return user;
    }

    public boolean setUser(User user) {

        // Anfrage-Statement erzeugen
        Statement statement = con.createStatement();

        // Insert-Statement erzeugen (Fragezeichen werden später ersetzt).
        String sql = "INSERT INTO user(uid, firstname, lastname, birth) " +
                "VALUES(null, #, #, #) ON DUPLICATE KEY UPDATE uid = '" + user.getUid() + "'";
        // Fragezeichen durch Parameter ersetzen
        sql.replaceFirst("#", user.getFirstname());
        sql.replaceFirst("#", user.getLastname());

        // SQL ausführen
        statement.execute(sql, new IConnectionInterface() {
            @Override
            public void actionCompleted() {

            }

            @Override
            public void handleInvalidSQLPacketException(InvalidSQLPacketException ex) {

            }

            @Override
            public void handleMySQLException(MySQLException ex) {

            }

            @Override
            public void handleIOException(IOException ex) {

            }

            @Override
            public void handleMySQLConnException(MySQLConnException ex) {

            }

            @Override
            public void handleException(Exception exception) {

            }
        });

        return true;
    }

    // --------------------------------------------------------------
    // Wasser
    /*public Water getWater(User user) {
        Water water = new Water();

        // Anfrage-Statement erzeugen
        Statement statement;
        try {
            statement = con.createStatement();

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
    }*/
}