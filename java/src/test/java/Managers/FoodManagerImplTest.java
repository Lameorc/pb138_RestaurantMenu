package Managers;

import entities.Food;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.DERBY;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class FoodManagerImplTest {
    private FoodManagerImpl manager;
    private EmbeddedDatabase db;
    @BeforeClass
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder().setType(DERBY).addScript("createTables.sql").build();
        manager = new FoodManagerImpl(db);
    }

    @AfterClass
    public void tearDown() throws Exception {
        db.shutdown();
    }

    @Test
    public void testCreateFood() throws Exception {
        Food foodAdded = new Food(null, "Svíčková", BigDecimal.valueOf(200), "", LocalDate.of(2016, 12, 12), null);
        manager.createFood(foodAdded);

        assertThat(foodAdded.getId()).isNotNull();

        Food foodReturned = manager.findFood(foodAdded.getId());
        assertThat(foodReturned).isEqualToComparingFieldByField(foodAdded);
    }

    @Test
    public void testUpdateFood() throws Exception {
        Food food = new Food(null, "Svíčková", BigDecimal.valueOf(200), "", LocalDate.of(2016, 12, 12), null);
        manager.createFood(food);

        food.setDate(LocalDate.of(2016,10,10));
        food.setDescription("Chutné");
        food.setName("Rajská");
        food.setPrice(BigDecimal.valueOf(350));

        manager.updateFood(food);

        Food retrievedFood = manager.findFood(food.getId());
        assertThat(retrievedFood).isEqualToComparingFieldByField(food);
    }

    @Test
    public void testRemoveFood() throws Exception {
        assertThat(manager.getAllFood()).isEmpty();

        Food food = new Food(null, "Svíčková", BigDecimal.valueOf(200), "", LocalDate.of(2016, 12, 12), null);
        manager.createFood(food);

        manager.removeFood(food);
        List<Food> foodInDb = manager.getAllFood();
        assertThat(foodInDb).doesNotContain(food);
        assertThat(foodInDb).isEmpty();
    }

    @Test
    public void testFindFood() throws Exception {
        Food food = new Food(null, "Svíčková", BigDecimal.valueOf(200), "", LocalDate.of(2016, 12, 12), null);
        manager.createFood(food);

        Food retrieved = manager.findFood(food.getId());
        assertThat(retrieved).isEqualToComparingFieldByField(food);
    }


}
