package Managers;

import entities.Food;
import entities.Menu;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.MenuMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Tomas Jochec on 15.06.2016.
 */
public class MenuManagerImpl implements MenuManager {
    private final JdbcTemplate jdbc;

    public MenuManagerImpl(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }

    @Override
    public void createMenu(Menu menu) {
        if(menu == null){
            throw new IllegalArgumentException("Menu can't be null when creating");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).
                withTableName("menus").usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("START_DATE", menu.getStartDate())
                .addValue("END_DATE", menu.getEndDate());
        Number id = insert.executeAndReturnKey(params);
        menu.setId(id.longValue());
    }

    @Override
    public void updateMenu(Menu menu) {
        if(menu == null){
            throw new IllegalArgumentException("Menu can't be null when updating");
        }
        jdbc.update("UPDATE food SET START_DATE=?"
                        + ",END_DATE=? "
                        + "where id=?",
                menu.getStartDate(),
                menu.getEndDate(),
                menu.getId());
    }

    @Override
    public void removeMenu(Menu menu) {
        if(menu == null){
            throw new IllegalArgumentException("Menu can't be null when removing");
        }
        jdbc.update("DELETE FROM menus WHERE id=? ", menu.getId());
    }

    @Override
    public Menu findMenu(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id can't be null when finding menu");
        }
        Menu menu;
        try{
            menu = jdbc.queryForObject("SELECT * FROM menus WHERE id=?",
                    (rs, row) -> new MenuMapper().mapRow(rs, row));
        }
        catch(EmptyResultDataAccessException e){
            menu = null;
        }
        return menu;
    }

    @Override
    public List<Menu> getAllMenus() {
        return jdbc.query("SELECT * FROM menus", (rs, row) -> new MenuMapper().mapRow(rs, row));
    }

    @Override
    public void assignFoodToMenu(Food food, Menu menu) {
        if(food == null){
            throw new IllegalArgumentException("Food can't be null when assigning");
        }
        if(menu == null){
            throw new IllegalArgumentException("Menu can't be null when assigning");
        }
        jdbc.update("UPDATE food set MENU_ID=? where id=?",
                    menu.getId(), food.getId());
    }
}
