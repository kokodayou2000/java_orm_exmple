package org.example.handler;

import lombok.NoArgsConstructor;
import org.example.annotation.Param;
import org.example.annotation.Table;
import org.example.conf.CustomMyBatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 代理类，针对与 selectById 等方法进行重写
 * @author deng
 * @date 2020-02-08 17:03
 */
@Component
@NoArgsConstructor
public class CustomMyBatisInvocationHandler implements InvocationHandler {

    @Autowired
    CustomMyBatisProperties customMyBatisProperties;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith("select")){
            return invokeSelect(proxy,method,args);
        }

        return null;
    }



    private Object invokeSelect(Object proxy, Method method, Object[] args) throws Throwable {
        String url = customMyBatisProperties.getUrl();
        String username = customMyBatisProperties.getUsername();
        String password = customMyBatisProperties.getPassword();

        String sql = createSql(proxy,method,args);

        try(Connection conn = DriverManager.getConnection(url,username,password);
                PreparedStatement statement = conn.prepareStatement(sql)
        ){
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Integer){
                    statement.setInt(i+1,(Integer)arg);
                }else if (arg instanceof String){
                    statement.setString(i+1,(String)arg);
                }
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return this.parseResult(resultSet,method.getReturnType() );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将jdbc查询的数据，转换成对象
     * @param resultSet
     * @param returnType
     * @return
     * @throws Exception
     */
    private Object parseResult(ResultSet resultSet, Class<?> returnType) throws Exception {
        Constructor<?> constructor = returnType.getConstructor();
        Object result = constructor.newInstance();
        Field[] declaredFields = returnType.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Object column = null;
            String name = declaredField.getName();
            Class<?> type = declaredField.getType();
            if (type == String.class){
                column = resultSet.getString(name);
            }else if (type == Integer.class){
                column = resultSet.getInt(name);
            }
            declaredField.setAccessible(true);
            declaredField.set(result,column);
        }
        return result;
    }


    private String createSql(Object proxy, Method method, Object[] args){
        StringBuilder sqlBuilder = new StringBuilder();

        String prefix = "select ";

        // 获取需要查询的数据
        Class<?> returnType = method.getReturnType();
        List<String> cols = this.getSelectCols(returnType);
        String fieldList = String.join(",",cols);

        sqlBuilder.append(prefix);
        sqlBuilder.append(fieldList);
        sqlBuilder.append(" from ");
        // 获取表名
        String tableName = this.getSelectTableName(returnType);
        sqlBuilder.append(tableName);
        sqlBuilder.append(" where ");

        String cond = this.getSelectWhere(method);
        sqlBuilder.append(cond);

        return sqlBuilder.toString();

    }

    private String getSelectWhere(Method method) {
        Parameter[] parameters = method.getParameters();
        String collect = Arrays.stream(parameters).map(parameter -> {
            Param annotation = parameter.getAnnotation(Param.class);
            String field = annotation.value();
            String condition = field + " = ? ";
            return condition;
        }).collect(Collectors.joining(" and "));
        return collect;
    }

    private String getSelectTableName(Class<?> returnType) {
        Table table = returnType.getAnnotation(Table.class);
        if (table != null){
            return table.value();
        }
        return null;
    }

    private List<String> getSelectCols(Class<?> returnType){
        Field[] declaredFields = returnType.getDeclaredFields();
        return Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
    }
}
