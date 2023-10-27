package org.deng.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.deng.domain.Stu;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public interface StuMapper {

    // 查询学生列表
    List<Stu> selectStuList();

    // 通过id获取学生详情
//    @Select("select * from mybatis_t.stu_t where s_id=#{sId}")
    Stu selectStuById(Integer sId);

    // 插入学生
//    public Integer insertStu(Stu stu);
//
//    // 批量插入新生
//    public Integer insertStuList(List<Stu> stuList);
//
//    // 更新学生信息
//    public Integer updateStu(Stu stu);
//
//    // 批量更新学生信息
//    public Integer updateStuList(List<Stu> stuList);
//
//    public List<Stu> queryStuNested();
//
//    public List<Stu> queryStuNestedLazy();
//
//    public List<Stu> queryStuList(RowBounds rowBounds);
}
