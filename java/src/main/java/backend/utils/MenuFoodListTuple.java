package backend.utils;

import backend.entities.Food;
import backend.entities.Menu;

import java.util.List;

/**
 * Created by Vojta Podhajsky on 26.06.2016.
 */
public class MenuFoodListTuple {
    Menu menu;
    List<Food> foodList;

    public MenuFoodListTuple(Menu menu, List<Food> foodList) {
        this.menu = menu;
        this.foodList = foodList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
