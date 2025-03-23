package org.example.factory;

import lombok.NoArgsConstructor;
import org.example.handler.CustomMyBatisInvocationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class MySqlSessionFactory {

    @Autowired
    CustomMyBatisInvocationHandler handler;

    public <T> T getMapper(Class<T> mapperClass){

        try {
            T result = (T) Proxy.newProxyInstance(
                    this.getClass().getClassLoader(),
                    new Class[]{mapperClass},
                    handler
            );
            return result;
        }catch (Exception e){
            return null;
        }
    }

}
