package com.cjgmj.jboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cjgmj.jboss.service.IUploadFileService;

@SpringBootApplication
public class SpringBootJbossApplication implements CommandLineRunner {

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJbossApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();

		String passwordAdmin = "admin";
		System.out.println(passwordEncoder.encode(passwordAdmin));

		String passwordUser = "user";
		System.out.println(passwordEncoder.encode(passwordUser));
	}

}
