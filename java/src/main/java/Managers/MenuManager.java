package Managers;

import entities.Food;
import entities.Menu;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public interface MenuManager {
    void createMenu(Menu menu);
    void updateMenu(Menu menu);
    void removeMenu(Menu menu);
    Menu findMenu(Long Id);
    List<Menu> getAllMenus();
    void assignFoodToMenu(Food food, Menu menu);
}
