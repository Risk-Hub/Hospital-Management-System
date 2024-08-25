package com.hsbc.dao;

import com.hsbc.model.Admin;
import com.hsbc.model.Doctor;
import com.hsbc.model.HospitalStaff;
import com.hsbc.model.User;

import java.sql.*;

public class DatabaseConnect {
    public static Connection initializeDb() throws ClassNotFoundException, SQLException {
        String dbName = "hospitalSystemDb";
        String dbUsername = "root";
        String dbPassword = "nishtha123";
        String dbUrl = "jdbc:mysql://localhost:3306/";
        String dbDriver = "com.mysql.cj.jdbc.Driver";

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbUrl + dbName, dbUsername, dbPassword);
    }
}
