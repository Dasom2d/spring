package com.spring.user.uoUse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tobi?useSSL=false";
        String user = "root";
        String pw = "localmysql1234";

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pw);

            System.out.println("[Database 연결 성공]");

        } catch (SQLException e) {
            System.out.println("[SQL Error : " + e.getMessage() +"]");
        } catch (ClassNotFoundException e1) {
            System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]");
        }

        return con;
    }
}
