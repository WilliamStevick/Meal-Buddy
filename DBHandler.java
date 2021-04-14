package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    Connection connection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mealbuddy","root","TyDB26Eagle!");


        return connection;
    }

}
