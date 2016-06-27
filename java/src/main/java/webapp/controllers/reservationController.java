package webapp.controllers;

import backend.Managers.ReservationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomáš Jochec on 22.06.2016.
 */
@Controller
public class reservationController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value="/reservation", method=RequestMethod.GET)
    public String reservationList(Model model) {
        //model.addAttribute("food", new Food());
        return "reservation";
    }

    @RequestMapping("/reservation/{id}")
    public String reservation(@PathVariable("id") long foodId,  Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");
        reservationManager.reserveFoodByUser(foodId, name);

        return "redirect:/";
    }
}
