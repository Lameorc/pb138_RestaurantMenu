package webapp.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vojta Podhajsky on 18.06.2016.
 */
@RestController
public class indexController {

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}
