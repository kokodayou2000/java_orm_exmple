package org.demo;

import java.sql.*;

public class _01_PureJdbc {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mybatis_t?characterEncoding=utf-8&serverTimezone=UTC";

    private static final String JDBC_USER = "root";

    private static final String JDBC_PWD = "123456";

    private static Connection conn;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {
        queryUser();
        addUser(6,"F");
        delUser(6);
    }

    public static Connection getConn() {
        if (conn == null){
            try {
                conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PWD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void queryUser() throws SQLException {
        User user = new User();
        ResultSet rs = null;
        conn = getConn();
        stmt = conn.createStatement();
        String sql = "select * from user_t";
        rs  = stmt.executeQuery(sql);
        while (rs.next()){
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("id: "+id+" name: "+name);
        }
    }

    public static void addUser(Integer id, String name) throws SQLException {
        conn = getConn();
        stmt = conn.createStatement();
        String sql = "insert into user_t (id,name) values (" + id +",'"+name+"');";
        System.out.println(sql);
        int i  = stmt.executeUpdate(sql);
        System.out.println(i);
    }
    public static void delUser(Integer id) throws SQLException {
        conn = getConn();
        stmt = conn.createStatement();
        String sql = "delete from user_t where id = "+id+" ;";
        System.out.println(sql);
        int i  = stmt.executeUpdate(sql);
        System.out.println(i);
    }



}
