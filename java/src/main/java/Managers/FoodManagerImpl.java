package Managers;

import entities.Food;
import entities.Menu;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.FoodMapper;


/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class FoodManagerImpl implements FoodManager {
    private final JdbcTemplate jdbc;
            
    
    public FoodManagerImpl(DataSource datasource) {        
        jdbc = new JdbcTemplate(datasource);
    }
    
    
    @Override
    public void createFood(Food food) {
        if(food==null){
            throw new IllegalArgumentException("Food can not be null when creating");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).
                withTableName("food").usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("food_name", food.getName())
                .addValue("price", food.getPrice())
                .addValue("description", food.getDescription())
                .addValue("food_date", Date.valueOf(food.getDate()))
                .addValue("menu_id", food.getMenuId());
        Number id = insert.executeAndReturnKey(params);
        food.setId(id.longValue());
    }

    @Override
    public void updateFood(Food food) {
        if(food == null){
            throw new IllegalArgumentException("Food can't be null when updating");
        }
        jdbc.update("UPDATE food SET food_name=?"
                + ",price=?,"
                + "description=?,"
                + "food_date=?,"
                + "menu_id=? "
                + "where id=?",
                food.getName(),
                food.getPrice(),
                food.getDescription(),
                Date.valueOf(food.getDate()),
                food.getMenuId(),
                food.getId());
    }

    @Override
    public void removeFood(Food food) {
        if(food == null){
            throw new IllegalArgumentException("Food can't be null when removing");
        }
        jdbc.update("DELETE FROM food WHERE id=? ", food.getId());
    }

    @Override
    public Food findFood(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id can't be null when finding food");
        }
        Food f;
        try{
            f = jdbc.queryForObject("SELECT * FROM food WHERE id=?",
                    (rs, row) -> new FoodMapper().mapRow(rs, row),
                    id);
        }
        catch(EmptyResultDataAccessException e){
            f = null;
        }        
        return f;
    }    

    @Override
    public List<Food> getAllFood() {
        return jdbc.query("SELECT * FROM food", (rs, row) -> new FoodMapper().mapRow(rs, row));
    }
}
