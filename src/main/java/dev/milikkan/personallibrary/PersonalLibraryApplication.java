package dev.milikkan.personallibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PersonalLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalLibraryApplication.class, args);
    }

}
