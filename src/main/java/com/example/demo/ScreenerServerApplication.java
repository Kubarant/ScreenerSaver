package com.example.demo;

//import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenerServerApplication {
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		
		
		SpringApplication.run(ScreenerServerApplication.class, args);
		//new ImageGetServer().start();
		
		
	}
}
