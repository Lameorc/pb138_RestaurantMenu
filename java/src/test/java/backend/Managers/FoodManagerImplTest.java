package backend.Managers;

import backend.entities.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.DERBY;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class FoodManagerImplTest {
    private FoodManagerImpl manager;
    private EmbeddedDatabase db;
    @BeforeTest
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder().setType(DERBY).setName("FoodManagerImplTest").addScript("createTables.sql").build();
        manager = new FoodManagerImpl(db);
    }

    @AfterTest
    public void tearDown() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(db);
        jdbc.execute("DROP TABLE food");
        jdbc.execute("DROP TABLE menus");
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
        food.setWeight("Chutné");
        food.setName("Rajská");
        food.setPrice(BigDecimal.valueOf(350));

        manager.updateFood(food);

        Food retrievedFood = manager.findFood(food.getId());
        assertThat(retrievedFood).isEqualToComparingFieldByField(food);
    }

    @Test
    public void testRemoveFood() throws Exception {
        Food food = new Food(null, "Svíčková", BigDecimal.valueOf(200), "", LocalDate.of(2016, 12, 12), null);
        manager.createFood(food);
        List<Food> foodInDb = manager.getAllFood();
        List<Long> ids = foodInDb.stream().map(Food::getId).collect(Collectors.toList());
        assertThat(ids).contains(food.getId());

        manager.removeFood(food);
        foodInDb.clear();
        ids.clear();
        foodInDb = manager.getAllFood();
        ids.addAll(foodInDb.stream().map(Food::getId).collect(Collectors.toList()));
        assertThat(ids).doesNotContain(food.getId());
    }

    @Test
    public void testFindFood() throws Exception {
        Food food = new Food(null, "Svíčková", BigDecimal.valueOf(200), "", LocalDate.of(2016, 12, 12), null);
        manager.createFood(food);

        Food retrieved = manager.findFood(food.getId());
        assertThat(retrieved).isEqualToComparingFieldByField(food);
    }


}
