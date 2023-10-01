package com.hmk.system.permission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.hmk.system.permission.repository")
@EntityScan("com.hmk.system.permission.entity")
@SpringBootApplication
public class SystemPermissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemPermissionApplication.class, args);
    }

}
