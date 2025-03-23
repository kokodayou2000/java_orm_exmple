package org.example.entity;

import org.example.annotation.Param;

public interface UserMapper {

    User selectUserById(@Param("id") int id);

}
