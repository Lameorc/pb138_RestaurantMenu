package backend.Managers;

import backend.entities.Menu;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.DERBY;

/**
 * Created by Tomas Jochec on 17.06.2016.
 */
public class MenuManagerImplTest {
    private MenuManagerImpl manager;
    private EmbeddedDatabase db;

    @BeforeTest
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder().setType(DERBY).addScript("createTables.sql").build();
        manager = new MenuManagerImpl(db);
    }

    @AfterTest
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
        Menu menu = new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 12, 26));
        manager.createMenu(menu);
        ArrayList<Long> ids;
        ids = manager.getAllMenus().stream().map(Menu::getId).collect(Collectors.toCollection(ArrayList::new));
        assertThat(ids).contains(menu.getId());

        manager.removeMenu(menu);
        ids.clear();
        ids = manager.getAllMenus().stream().map(Menu::getId).collect(Collectors.toCollection(ArrayList::new));

        assertThat(ids).doesNotContain(menu.getId());
    }

    @Test
    public void testFindMenu() throws Exception {
        Menu menu = new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 12, 26));
        manager.createMenu(menu);

        Menu retrieved = manager.findMenu(menu.getId());
        assertThat(retrieved).isEqualToComparingFieldByField(menu);
    }

}
