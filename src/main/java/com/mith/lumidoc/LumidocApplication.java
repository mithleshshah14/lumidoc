package com.mith.lumidoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(exclude = {
		dev.langchain4j.openai.spring.AutoConfig.class
})
public class LumidocApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(LumidocApplication.class, args);
	}

}
