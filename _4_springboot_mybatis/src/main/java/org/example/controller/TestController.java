package org.example.controller;


import jakarta.annotation.Resource;
import org.example.mapper.TestMapper;
import org.example.model.TestDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    TestMapper testMapper;

    @GetMapping("/getAll")
    public List<TestDO> getAll(){
        System.out.println(testMapper);
        return testMapper.selectAll();
    }
}
