package we.chrisoli.lifestyletracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private Connection con = null;
    String host = "freedb.tech";
    String port = "3306";
    String db = "freedbtech_LifestyleTracker";
    String username = "freedbtech_olichris";
    String password = "tracker";

    public Connection startConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber für JDBC
            // Schnittstellen laden.

            // Verbindung zur JDBC-Datenbank herstellen.
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?" + "user="
                    + username + "&" + "password=" + password);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
        } catch (SQLException e) {
            System.out.println("Verbindung nicht moglich");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return con;
    }

    public void closeConnection() {
        try{
            con.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
