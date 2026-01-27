package com.globits.demo;

import com.globits.demo.config.ModelConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.globits.demo.model.Television;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//
		SpringApplication.run(DemoApplication.class, args);

		//
		// sử dụng AnnotationConfigApplicationContext thay vì ClassPathXmlApplicationContext
		// do DI bằng config class chứ kphai xml
		ApplicationContext context = new AnnotationConfigApplicationContext(ModelConfig.class);

		//gọi bean
		Television television = context.getBean("televisionBean", Television.class);
		// gọi method của bean trên
		television.turnOn();

	}

}
