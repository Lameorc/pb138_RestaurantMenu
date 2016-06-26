package backend.Managers;

import backend.entities.Food;
import backend.entities.Reservation;
import org.junit.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.init.ScriptUtils;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.DERBY;

/**
 * Created by Vojta Podhajsky on 24.06.2016.
 */
public class ReservationManagerImplTest {


    private ReservationManagerImpl manager;
    private EmbeddedDatabase db;

    private Food food;

    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder()
                .setType(DERBY)
                .setName("ReservationManagerImplTest")
                .addScript("createTables.sql")
                .addScript("test/seedTests.sql")
                .build();
        manager = new ReservationManagerImpl(db);
        FoodManager fm = new FoodManagerImpl(db);
        food = fm.getAllFood().get(0);
    }

    @After
    public void tearDown() throws Exception {
        Resource resource = new ClassPathResource("dropTables.sql");
        ScriptUtils.executeSqlScript(db.getConnection(), resource);
        food = null;
        db.shutdown();
    }
    @Test
    public void testGetAllReservations() throws Exception {
        FoodManager fm = new FoodManagerImpl(db);
        fm.createFood(food);

        List<Reservation> reservations = manager.getAllReservations();
        assertThat(reservations).hasSize(1);
        for(Reservation r: reservations){
            assertThat(r.getFoodId()).isNotEqualTo(food.getId());
        }
    }

    @Test
    public void testReserveFoodByUser() throws Exception {
        List<Reservation> reservations = manager.getAllReservations();
        assertThat(reservations).hasSize(1);

        manager.reserveFoodByUser(food.getId(), "guest");
        reservations = manager.getAllReservations();
        assertThat(reservations).hasSize(2);
        List<Long> foodIds = reservations.stream().map(Reservation::getFoodId).collect(Collectors.toList());
        assertThat(foodIds).contains(food.getId());
    }

    @Test
    public void testGetFoodReservedByUser() throws Exception {
        String userName = "guest";
        List<Food> foodReserved = manager.getFoodReservedByUser(userName);
        assertThat(foodReserved).contains(food);
    }

}
