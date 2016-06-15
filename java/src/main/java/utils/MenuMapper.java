/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Food;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Menu;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jochec
 */
public class MenuMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet rs, int i) throws SQLException {
        return new Menu(
                rs.getLong("ID"),
                rs.getDate("START_DATE").toLocalDate(),
                rs.getDate("END_DATE").toLocalDate()
        );
    }

}
