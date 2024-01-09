package com.uas.humanresource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;

import com.uas.humanresource.repository.StaffRepository;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.uas.humanresource.repository")
@ComponentScan({
		"com.uas.humanresource.repository",
		"com.uas.humanresource.service",
		"com.uas.humanresource.controller",
		"com.uas.humanresource.domain",
})
public class HumanResourceManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceManagementSystemApplication.class, args);
	}

	@Autowired
	private StaffRepository staffRepository;

	public static Authentication authenticatedUser;
	@Override
	public void run(String... args) throws Exception {

		
	}

}
