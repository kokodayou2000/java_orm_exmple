package org.deng.action;


import org.deng.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User goodObj = new User(4, "K");
        session.save(goodObj);

        // 注释该行可以看到 事务的执行效果
        session.delete(goodObj);

        // 创建一个主键重复的对象
        User errorObj = new User(4, "K");
        session.save(errorObj);
        transaction.commit();
        session.close();
    }
}
