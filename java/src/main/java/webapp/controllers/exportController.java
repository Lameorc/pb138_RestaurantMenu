/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.controllers;

import backend.Managers.MenuManager;
import backend.Managers.XmlManager;
import backend.entities.Menu;
import backend.utils.MenuFoodListTuple;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webapp.Application;

/**
 *
 * @author vojtech.podhajsky
 */
public class exportController {
    @Autowired
    private ApplicationContext ctx;
    
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFile() {
        MenuManager menuManager = (MenuManager) ctx.getBean("menuManager");
        List<Menu> menus = menuManager.getAllMenus();

        menus.removeIf(m ->
                !(m.getStartDate().getDayOfYear() == LocalDate.now().getDayOfYear() ||
                        (m.getStartDate().isBefore(LocalDate.now())
                                && m.getEndDate().isAfter(LocalDate.now()))||
                        m.getEndDate().getDayOfYear() == LocalDate.now().getDayOfYear())
        );

        if(!menus.isEmpty()) {
            MenuFoodListTuple tuple = new MenuFoodListTuple();
            tuple.setMenu(menus.get(0));
            tuple.setFoodList(menuManager.getFoodInMenu(menus.get(0)));

            XmlManager xmlManager = (XmlManager) ctx.getBean("xmlManager");
            try {
                xmlManager.generateXml("src/main/resources/xml/menu.xml", tuple);
            } catch (IOException | DatatypeConfigurationException e) {
                System.err.println("Failed to generate xml file");
            }
        }
        
        return new FileSystemResource(new File("src/main/resources/xlm/export.xml"));
    }
}
