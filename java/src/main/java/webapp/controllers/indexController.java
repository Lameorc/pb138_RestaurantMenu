package webapp.controllers;

import backend.Managers.FoodManager;
import backend.Managers.MenuManager;
import backend.entities.Food;
import backend.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.*;

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
                !(m.getStartDate().getDayOfYear() ==LocalDate.now().getDayOfYear() ||
                        (m.getStartDate().isBefore(LocalDate.now())
                        && m.getEndDate().isAfter(LocalDate.now()))||
                        m.getEndDate().getDayOfYear() == LocalDate.now().getDayOfYear())
        );


        TreeMap<LocalDate, List<Food>> foodMap = new TreeMap<>();
        LocalDate startDate = null;
        LocalDate endDate = null;
        for (Menu menu : menus){
            if(startDate == null){
                startDate = menu.getStartDate();
            }
            else if(startDate.isAfter(menu.getStartDate())){
                startDate = menu.getStartDate();
            }
            if(endDate == null){
                endDate = menu.getEndDate();
            }
            else if(endDate.isBefore(menu.getEndDate())){
                endDate = menu.getEndDate();
            }
            for(Food f : menuManager.getFoodInMenu(menu)){
                LinkedList<Food> food =  new LinkedList<Food>();
                food.add(f);
                foodMap.merge(f.getDate(), food,(l1, l2) -> {l1.addAll(l2); return l1;});
            }
        }
        model.addAttribute("foodMap", foodMap);
        model.addAttribute("menu", new Menu(null, startDate, endDate));

        return "index";
    }
}
