package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import java.io.IOException;


@SpringBootApplication
public class Spring05ShellMain {

	public static void main(String[] args) throws IOException {

		ApplicationContext context = SpringApplication.run(Spring05ShellMain.class, args);

	}

}
