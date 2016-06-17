package Managers;

import entities.Menu;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.DERBY;
import static org.testng.Assert.*;

/**
 * Created by Tomas Jochec on 17.06.2016.
 */
public class MenuManagerImplTest {
    private MenuManagerImpl manager;
    private EmbeddedDatabase db;
    @BeforeMethod
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder().setType(DERBY).addScript("createTables.sql").build();
        manager = new MenuManagerImpl(db);
    }

    @AfterClass
    public void tearDown() throws Exception {
        db.shutdown();
    }

    @Test
    public void testCreateMenu() throws Exception {
        Menu menuAdded = new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 12, 26));
        manager.createMenu(menuAdded);

        assertThat(menuAdded.getId()).isNotNull();

        Menu menuReturned = manager.findMenu(menuAdded.getId());
        assertThat(menuReturned).isEqualToComparingFieldByField(menuAdded);
    }

    @Test
    public void testUpdateMenu() throws Exception {
        Menu menu = new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 12, 26));
        manager.createMenu(menu);

        menu.setStartDate(LocalDate.of(2016,6,27));
        menu.setEndDate(LocalDate.of(2016,7,3));

        manager.updateMenu(menu);

        Menu retrievedFood = manager.findMenu(menu.getId());
        assertThat(retrievedFood).isEqualToComparingFieldByField(menu);
    }

    @Test
    public void testRemoveMenu() throws Exception {
        assertThat(manager.getAllMenus()).isEmpty();

        Menu menu = new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 12, 26));
        manager.createMenu(menu);

        manager.removeMenu(menu);
        List<Menu> menuInDb = manager.getAllMenus();
        assertThat(menuInDb).doesNotContain(menu);
        assertThat(menuInDb).isEmpty();
    }

    @Test
    public void testFindMenu() throws Exception {
        Menu menu = new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 12, 26));
        manager.createMenu(menu);

        Menu retrieved = manager.findMenu(menu.getId());
        assertThat(retrieved).isEqualToComparingFieldByField(menu);
    }

}
