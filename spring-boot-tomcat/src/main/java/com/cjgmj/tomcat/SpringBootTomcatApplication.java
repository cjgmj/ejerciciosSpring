package com.cjgmj.tomcat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cjgmj.tomcat.service.IUploadFileService;

@SpringBootApplication
public class SpringBootTomcatApplication implements CommandLineRunner {

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTomcatApplication.class, args);
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
