package com.kuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@MapperScan("com.kuang.mapper")

public class KuangstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KuangstudyApplication.class, args);
	}

}