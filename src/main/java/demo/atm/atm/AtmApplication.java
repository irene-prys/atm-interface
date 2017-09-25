package demo.atm.atm;

import demo.atm.atm.controllers.AtmController;
import demo.atm.atm.utils.DefaultDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AtmApplication extends SpringBootServletInitializer {// todo: rename package
	@Autowired
	private DefaultDataInitializer dataInitializer;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AtmApplication.class);
	}


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AtmApplication.class, args);
		context.getBean(DefaultDataInitializer.class).initCards();
	}
}
