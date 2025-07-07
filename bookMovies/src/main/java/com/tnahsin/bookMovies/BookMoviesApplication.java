package com.tnahsin.bookMovies;

import com.mongodb.client.MongoClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class BookMoviesApplication implements DisposableBean {

	@Autowired
	private MongoClient mongoClient;

	@Override
	public void destroy() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BookMoviesApplication.class, args);
	}

}
