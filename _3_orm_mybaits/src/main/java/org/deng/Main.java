package org.deng;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.deng.domain.Stu;
import org.deng.mapper.StuMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String resource  = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try(SqlSession sqlSession = sessionFactory.openSession()){
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            mapper.selectStuList().forEach((obj)->{
                System.out.println(obj.getsId());
            });
            System.out.println(mapper.selectStuById(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
