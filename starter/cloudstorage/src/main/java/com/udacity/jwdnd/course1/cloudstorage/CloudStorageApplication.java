package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudStorageApplication implements ApplicationRunner {
	public final UserService userService;

	public CloudStorageApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
//		User a = new User(null, "a", "a", "a", "a", "a");
//		this.userService.createUser(a);
	}
}
