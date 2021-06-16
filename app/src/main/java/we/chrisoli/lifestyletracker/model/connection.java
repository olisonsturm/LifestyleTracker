package we.chrisoli.lifestyletracker.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    String db_ip;
    String db_port;
    String db_name;
    Connection conn = null;
    String user;
    String pass;
    public connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connection = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name;
            try {
                conn = DriverManager.getConnection(connection, user, pass);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
