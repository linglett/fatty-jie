package driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class MySQLDrive {
    private static Connection DriverAndConnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "20001228");
        }  catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Statement GetStatement() throws SQLException {
        return DriverAndConnect().createStatement();
    }
    public static PreparedStatement GetPreparedStatement(String Sql) throws SQLException {
        return DriverAndConnect().prepareStatement(Sql);
    }

}
