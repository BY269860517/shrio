package com.learn.shrio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.shrio.dao")
public class ShrioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShrioApplication.class, args);
	}

}
