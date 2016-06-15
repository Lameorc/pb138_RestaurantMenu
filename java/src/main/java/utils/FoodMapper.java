/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Food;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author vojtech.podhajsky
 */
public class FoodMapper implements RowMapper<Food> {

    @Override
    public Food mapRow(ResultSet rs, int i) throws SQLException {
        return new Food(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getBigDecimal("price"),
                rs.getString("description"),
                rs.getDate("food_date").toLocalDate(),
                rs.getLong("menu_id")
        );
    }

}