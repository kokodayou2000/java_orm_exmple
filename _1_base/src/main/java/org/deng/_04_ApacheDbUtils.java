package org.deng;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class _04_ApacheDbUtils {
    // Examines both filesystem and classpath for .properties file
    private static HikariConfig config = new HikariConfig("_1_base/src/main/resources/hikari.properties");
    private static HikariDataSource ds = new HikariDataSource(config);
    private static QueryRunner queryRunner;
    public static void main(String[] args) throws SQLException {
       queryRunner = new QueryRunner(ds);
       String sql = "select * from user_t";
        List<User> query = queryRunner.query(sql, (ResultSetHandler<List<User>>) rs -> {
            ArrayList<User> list = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                list.add(user);
            }
            return list;
        });
        query.forEach(System.out::println);
    }
}
