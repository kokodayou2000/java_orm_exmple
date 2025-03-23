package org.example.entity;


import lombok.Data;
import org.example.annotation.Table;

@Data
@Table("db_user")
public class User {

    private Integer id;

    private String name;

}
