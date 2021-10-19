package com.spring.user.uoUse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {

    public Connection makeNewConnection()  throws ClassNotFoundException, SQLException {
        Class.forName("com.my");
        Connection c = DriverManager.getConnection("jdbc:...");

        return c;
    }


}
