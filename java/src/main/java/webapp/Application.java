package webapp;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by Vojta Podhajsky on 18.06.2016.
 */

@SpringBootApplication
@ImportResource("classpath:context.xml")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    @Autowired
    private TemplateResolver templateResolver;
    
    @Bean
    public TemplateResolver templateResolver(){
        return templateResolver;
    };

    public static String ROOT = "uploads";

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(ROOT).mkdir();
        };
    }
}
