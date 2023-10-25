package org.demo;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class _03_Druid {
    // Examines both filesystem and classpath for .properties file

    private static final Properties props ;
    static {
        String Config_PATH = "druid.properties";
        props = new Properties();
        InputStream inS = _03_Druid.class.getClassLoader().getResourceAsStream(Config_PATH);
        try {
            props.load(inS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static DruidDataSource ds;
    private static Connection conn = null;
    private static Statement stmt = null;
    public static void main(String[] args) throws SQLException {
        ds = new DruidDataSource();
        ds.configFromPropeties(props);
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
