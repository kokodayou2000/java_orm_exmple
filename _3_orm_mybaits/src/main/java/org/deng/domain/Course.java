package org.deng.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Course {

    @JsonProperty("c_id")
    private Integer cId;

    @JsonProperty("c_name")
    private String cName;

    @JsonProperty("stu_list")
    private List<Stu> stuList;
}
