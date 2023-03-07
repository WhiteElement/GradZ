package com.manu.GradeR;

import com.manu.GradeR.model.User;
import com.manu.GradeR.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GradeRApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradeRApplication.class, args);
	}


//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder passwordEncoder) {
//		return args -> {
//			users.save(new User("test", passwordEncoder.encode("password"), "ROLE_TEACHER"));
//		};
//	}
}
