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

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Tomáš Jochec on 22.06.2016.
 */
@Controller
public class importController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value="/import", method=RequestMethod.GET)
    public String foodForm(Model model) {
        return "import";
    }
}
