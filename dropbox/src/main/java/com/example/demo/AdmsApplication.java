package com.example.demo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//public class AdmsApplication {
public class AdmsApplication extends SpringBootServletInitializer{
	
	
	private static Logger logger = LogManager.getLogger(AdmsApplication.class);
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		try {
			logger.info("Creating Connection With MySql");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			logger.error("Error In Creating Connection"+ ex.getMessage());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AdmsApplication.class, args);
		logger.info("Application Starting");
	}

}
