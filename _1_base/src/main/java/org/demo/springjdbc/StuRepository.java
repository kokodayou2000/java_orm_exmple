package org.demo.springjdbc;

import org.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StuRepository{

    @Autowired
    private JdbcTemplate template;

    public void addStu(Integer id,String name){
        String sql = "insert into user_t (id,name) values (" + id +",'"+name+"');";
        System.out.println(sql);
        template.execute(sql);
        System.out.println("finished");
    }

    public void delStu(Integer id){
        String sql = "delete from user_t  where id = " + id +" ;";
        System.out.println(sql);
        template.execute(sql);
        System.out.println("finished");
    }

    public List<User> queryStu(){

        System.out.println("query");
        List<User> query = template.query("select * from user_t", (resultSet, i) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new User(id, name);
        });
        System.out.println("finished");
        return query;

    }
}
