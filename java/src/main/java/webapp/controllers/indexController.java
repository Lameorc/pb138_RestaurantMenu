package webapp.controllers;

import backend.Managers.MenuManager;
import backend.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 18.06.2016.
 */
@Controller
public class indexController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping("/")
    public String index(Model model){
        MenuManager menuManager = (MenuManager) context.getBean("menuManager");
        List<Menu> menus = menuManager.getAllMenus();

        menus.removeIf(m ->
                !(m.getStartDate().isBefore(LocalDate.now())
                        && m.getEndDate().isAfter(LocalDate.now())));
        model.addAttribute("menus", menus);
        return "index";
    }
}
