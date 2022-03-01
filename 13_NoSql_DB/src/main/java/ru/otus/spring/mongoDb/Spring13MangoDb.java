package ru.otus.spring.mongoDb;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Spring13MangoDb {

	public static void main(String[] args) {
		SpringApplication.run(Spring13MangoDb.class, args);
	}

}
