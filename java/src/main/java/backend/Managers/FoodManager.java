package backend.Managers;

import backend.entities.Food;

import java.util.List;

/**
 * Manager interface for working with Food class. Implementations should work with one specific data source type.
 * 
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public interface FoodManager {
    /**
     * Creates food
     * @param food to be created
     */
    void createFood(Food food);
    /**
     * Updates food, food must have id set, so it's possible to determine which food to update. The rest of parameters are changed according to the Food object passed.
     * @param food Updated object which will replace one with the same id
     */
    void updateFood(Food food);
    /**
     * Removes food from datasource.
     * @param food which will be removed
     */
    void removeFood(Food food);
    /**
     * Remove food using id
     * @param id of food to remove
     */
    void removeFood(long id);
    /**
     * Find food in using id
     * @param id
     * @return food with specified id. Null if no such food could be found
     */
    Food findFood(Long id);
    /**
     * Get all food which is in database
     * @return List of all food found. The list is empty if no food is found.
     */
    List<Food> getAllFood();
}
