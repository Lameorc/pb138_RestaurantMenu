package Managers;

import entities.Food;
import entities.Menu;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class MenuManagerImpl implements MenuManager {
    @Override
    public void createMenu(Menu menu) {

    }

    @Override
    public void updateMenu(Menu menu) {

    }

    @Override
    public void removeMenu(Menu menu) {

    }

    @Override
    public Menu findMenu(Long Id) {
        return null;
    }

    @Override
    public List<Menu> getAllMenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu findMenuWithFood(Food food) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assignFoodToMenu(Food food, Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
