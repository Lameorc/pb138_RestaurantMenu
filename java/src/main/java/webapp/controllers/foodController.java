package webapp.controllers;

import backend.Managers.FoodManager;
import backend.Managers.MenuManager;
import backend.entities.Food;
import backend.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Tomáš Jochec on 22.06.2016.
 */
@Controller
public class foodController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value="/food", method=RequestMethod.GET)
    public String foodForm(Model model) {
        model.addAttribute("food", new Food());
        return "food";
    }

    @RequestMapping(value="/food", method=RequestMethod.POST)
    public String foodSubmit(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @ModelAttribute @Valid Food food, BindingResult bindingResult, Model model) {

        MenuManager menuManager = (MenuManager) context.getBean("menuManager");
        List<Menu> menus = menuManager.getAllMenus();

        menus.removeIf(m ->
                !(m.getStartDate().isBefore(LocalDate.now())
                        && m.getEndDate().isAfter(LocalDate.now())));


        food.setMenuId(menus.get(0).getId());
        food.setDate(date);


        FoodManager foodManager = (FoodManager) context.getBean("foodManager");

        if (food.getId() == null) {
            foodManager.createFood(food);
        } else {
            foodManager.updateFood(food);
        }


        return "redirect:/food";
    }

    @RequestMapping("food/delete/{id}")
    public String deleteFood(@PathVariable("id") long id){

        FoodManager foodManager = (FoodManager) context.getBean("foodManager");
        foodManager.removeFood(id);

        return "redirect:/";
    }

    @RequestMapping("/food/edit/{id}")
    public String editFood(@PathVariable("id") long id,  Model model){
        FoodManager foodManager = (FoodManager) context.getBean("foodManager");
        Food food = foodManager.findFood(id);

        model.addAttribute("food", food);
        return "food";
    }

}
