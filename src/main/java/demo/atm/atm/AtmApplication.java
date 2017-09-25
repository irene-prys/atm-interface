package demo.atm.atm;

import demo.atm.atm.controllers.AtmController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AtmApplication extends SpringBootServletInitializer {// todo: rename package

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AtmApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}
}
