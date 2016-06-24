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
                !(m.getStartDate().isBefore(LocalDate.now())
                        && m.getEndDate().isAfter(LocalDate.now())));
        model.addAttribute("menus", menus);

        TreeMap<LocalDate, List<Food>> foodMap = new TreeMap<>();
        for (Menu menu : menus){
            for(Food f : menuManager.getFoodInMenu(menu)){
                LinkedList<Food> food =  new LinkedList<Food>();
                food.add(f);
                foodMap.merge(f.getDate(), food,(l1, l2) -> {l1.addAll(l2); return l1;});
            }
        }
        model.addAttribute("foodMap", foodMap);

        //TODO use "foodMap" instead of "foods"
        FoodManager foodManager = (FoodManager) context.getBean("foodManager");
        List<Food> foods = foodManager.getAllFood();
        //model.addAttribute("foods", foods);

        return "index";
    }
}
