package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

public class JDBCtest {
    Connection connection;

    JDBCtest() throws ClassNotFoundException, SQLException {
        connection = null;
    }

    void setConnect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/test_database";
        String name = "test_user";
        String password = "12345";

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, name, password);
    }

    ArrayList<Person> getPersons() throws SQLException {
        ArrayList<Person> list = new ArrayList<Person>();
        Statement statement = null;

        statement = connection.createStatement();
        ResultSet result1 = statement.executeQuery("SELECT * FROM public.people");
        while (result1.next()) {
            Person person = new Person();
            person.setId(result1.getInt("id"));
            person.setName(result1.getString("name"));
            person.setAge(result1.getInt("age"));
            list.add(person);
        }
        return list;
    }

}
