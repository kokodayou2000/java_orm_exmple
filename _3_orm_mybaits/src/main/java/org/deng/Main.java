package org.deng;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.deng.domain.Stu;
import org.deng.mapper.StuMapper;
import org.deng.objectFactory.ObjectFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String resource  = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // sqlSession 中的 executor 中的 caches中保存了缓存信息
        try(SqlSession sqlSession = sessionFactory.openSession()){
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            mapper.selectStuList().forEach((obj)->{
                System.out.println(obj.getsId());
            });
            Stu stu = mapper.selectStuById(1);
            System.out.println(stu);
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectFactory objectFactory = new ObjectFactory();
        Stu stu = objectFactory.create(Stu.class);
        System.out.println(stu);
    }
}
