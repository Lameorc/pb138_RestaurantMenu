package webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vojta Podhajsky on 18.06.2016.
 */
@Controller
public class indexController {

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}
