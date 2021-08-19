package com.example.demo;

import com.example.models.User;
import com.example.controller.UserController;
import com.example.service.*;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MappedTypes(User.class)
@MapperScan("com.example.mapper")
@SpringBootApplication
@ComponentScan(basePackageClasses={UserController.class, JWTManager.class, Bcrypt.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
