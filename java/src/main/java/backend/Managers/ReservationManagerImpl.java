package backend.Managers;

import backend.entities.Food;
import backend.entities.Reservation;
import backend.utils.FoodMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 24.06.2016.
 */
public class ReservationManagerImpl implements ReservationManager {
    private JdbcTemplate jdbc;

    public ReservationManagerImpl(DataSource ds) {
        jdbc = new JdbcTemplate(ds);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return jdbc.query("SELECT * FROM reservation",
                (rs, row) -> new Reservation(rs.getString("person"), rs.getLong("food_id")));
    }


    public List<Food> getAllFood() {
        List<Long> ids = jdbc.query("SELECT DISTINCT food_id FROM RESERVATION", (rs, row)-> rs.getLong(0));
        ArrayList<Food> food = new ArrayList<>();
        for(Long id : ids){
            food.addAll(jdbc.query("SELECT * FROM FOOD WHERE ID=?",(rs, row) -> new FoodMapper().mapRow(rs, row), id));
        }
        return food;
    }

    @Override
    public void reserveFoodByUser(Long foodId, String userName) {
        if(foodId == null){
            throw new IllegalArgumentException("Food can't be null");
        }
        if(userName == null){
            throw new IllegalArgumentException("UserName can't be null");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName("reservation");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("person", userName)
                .addValue("food_id", foodId);
        insert.execute(params);
    }

    @Override
    public void cancelReservationByUser(Long foodId, String userName) {
        if(foodId == null){
            throw new IllegalArgumentException("Food can't be null");
        }
        if(userName == null){
            throw new IllegalArgumentException("UserName can't be null");
        }
        jdbc.update("DELETE FROM RESERVATION WHERE PERSON = ? AND FOOD_ID = ?",
                "'" + userName + "'", foodId);
    }

    @Override
    public List<Food> getFoodReservedByUser(String userName) {
        if(userName == null){
            throw new IllegalArgumentException("UserName can't be null");
        }
        return jdbc.query("SELECT * FROM FOOD WHERE ID IN (SELECT FOOD_ID FROM RESERVATION WHERE RESERVATION.PERSON = ?)",
                (rs, row)-> new FoodMapper().mapRow(rs, row), userName);
    }
}
