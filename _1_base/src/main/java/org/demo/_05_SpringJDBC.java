package org.demo;

import org.demo.springjdbc.SpringConfig;
import org.demo.springjdbc.StuRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class _05_SpringJDBC {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        StuRepository iocBean = ioc.getBean(StuRepository.class);
        iocBean.addStu(10,"K");
        iocBean.queryStu().forEach(System.out::println);
        iocBean.delStu(10);
    }
}

