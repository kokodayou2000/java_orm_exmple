package org.demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class _02_Hikari {
    // Examines both filesystem and classpath for .properties file
    private static HikariConfig config = new HikariConfig("_1_base/src/main/resources/hikari.properties");
    private static HikariDataSource ds = new HikariDataSource(config);
    private static Connection conn = null;
    private static Statement stmt = null;
    public static void main(String[] args) throws SQLException {
        conn = ds.getConnection();
        stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from user_t");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id: "+id+" name: "+name);
        }

    }
}
