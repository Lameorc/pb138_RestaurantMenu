package backend.Managers;

import backend.entities.Food;
import backend.entities.Menu;
import java.util.List;

/**
 * Interface for working with {@link Menu} objects
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public interface MenuManager {
    /**
     * creates given menu
     * @param menu to be created. When creating id should be null. After and if creation is successful, the id is set to the object
     */
    void createMenu(Menu menu);
    /**
     * updates menu based on id
     * @param menu which will be updated. Id parameter is used to determine which menu should be updated.
     */
    void updateMenu(Menu menu);
    /**
     * removed menu
     * @param menu to remove 
     */
    void removeMenu(Menu menu);
    /**
     * find menu using id
     * @param Id of menu which we are looking for
     * @return Menu with specified id
     */
    Menu findMenu(Long Id);
    /**
     * @return All available menus, blank List is there are none.
     */
    List<Menu> getAllMenus();
    /**
     * Assigns {@link Food} to {@link Menu}. Both need to be already created
     * @param food to assign
     * @param menu to which we assign
     */
    void assignFoodToMenu(Food food, Menu menu);
    /**
     * Gets all food in menu
     * @param menu must be already created
     * @return List of food in menu, empty list if menu is empty
     */
    List<Food> getFoodInMenu(Menu menu);
}
