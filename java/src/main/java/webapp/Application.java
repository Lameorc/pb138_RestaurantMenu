package webapp;


import java.io.File;

import backend.Managers.MenuManager;
import backend.Managers.XmlManager;
import backend.entities.Menu;
import backend.utils.MenuFoodListTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.springframework.boot.CommandLineRunner;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 18.06.2016.
 */

@SpringBootApplication
@ImportResource("classpath:context.xml")
public class Application {
    @Autowired
    private ApplicationContext ctx;

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

    @Scheduled(cron = "* * * * Mon")
    public void generateXmlForThisWeek() {
        MenuManager menuManager = (MenuManager) ctx.getBean("menuManager");
        List<Menu> menus = menuManager.getAllMenus();

        menus.removeIf(m ->
                !(m.getStartDate().getDayOfYear() == LocalDate.now().getDayOfYear() ||
                        (m.getStartDate().isBefore(LocalDate.now())
                                && m.getEndDate().isAfter(LocalDate.now()))||
                        m.getEndDate().getDayOfYear() == LocalDate.now().getDayOfYear())
        );

        if(menus.size() != 0) {
            MenuFoodListTuple tuple = new MenuFoodListTuple();
            tuple.setMenu(menus.get(0));
            tuple.setFoodList(menuManager.getFoodInMenu(menus.get(0)));

            XmlManager xmlManager = (XmlManager) ctx.getBean("xmlManager");
            try {
                xmlManager.generateXml("src/main/resources/xml/menu.xml", tuple);
            } catch (IOException | DatatypeConfigurationException e) {
                e.printStackTrace();
                System.err.println("Failed to generate xml file");
            }
            xmlManager.generateHtmlFromXml("src/main/resources/xml/menu.xml", "src/main/resources/template/print.html");
        }
    }
}
