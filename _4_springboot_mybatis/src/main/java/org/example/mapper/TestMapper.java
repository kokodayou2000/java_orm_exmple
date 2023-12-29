package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.TestDO;

import java.util.List;

public interface TestMapper {

    List<TestDO> selectAll();
}
