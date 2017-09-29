package demo.atm;

import demo.atm.utils.DefaultDataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AtmApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AtmApplication.class);
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AtmApplication.class, args);
        context.getBean(DefaultDataInitializer.class).initCards();
    }
}
