package org.example.conf;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
public class CustomMyBatisProperties {

    @Value("${custom.mybatis.url}")
    String url;

    @Value("${custom.mybatis.username}")
    String username;

    @Value("${custom.mybatis.password}")
    String password;
}
