package backend.Managers;

import backend.entities.Food;

import java.util.List;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public interface FoodManager {
    void createFood(Food food);
    void updateFood(Food food);
    void removeFood(Food food);
    Food findFood(Long id);
    List<Food> getAllFood();
}
