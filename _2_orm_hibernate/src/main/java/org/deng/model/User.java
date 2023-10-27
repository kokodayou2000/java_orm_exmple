package org.deng.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Target;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user_t")
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    private String name;
}
