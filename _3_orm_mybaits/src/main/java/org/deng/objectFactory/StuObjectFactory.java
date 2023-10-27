package org.deng.objectFactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.deng.domain.Stu;

import java.util.List;
import java.util.Properties;

public class StuObjectFactory extends DefaultObjectFactory  {
    @Override
    public <T> T create(Class<T> type) {
        if (type.equals(Stu.class)){
            Stu stu = new Stu();
            stu.setsName(stu.getsName()+" SomeThing");
            return (T) stu;
        }
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }
}
