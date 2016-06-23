package webapp.controllers;

import backend.Managers.FoodManager;
import backend.entities.Food;
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

        food.setDate(date);
        model.addAttribute("food", food);

        FoodManager foodManager = (FoodManager) context.getBean("foodManager");
        foodManager.createFood(food);

        return "food";
    }

    @RequestMapping("food/delete/{id}")
    public String deleteFood(@PathVariable("id") long id){

        FoodManager foodManager = (FoodManager) context.getBean("foodManager");
        foodManager.removeFood(id);

        return "redirect:/";
    }

}
