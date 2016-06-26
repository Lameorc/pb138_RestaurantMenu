package backend.Managers;

import backend.entities.Food;
import backend.entities.Menu;
import backend.utils.MenuFoodListTuple;
import backend.xmlObjects.Menus;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.support.GenericMessage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Vojta Podhajsky on 21.06.2016.
 */
public class XmlManagerTest {

    private XmlManager manager;

    @BeforeMethod
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test/testContext.xml");
        manager = (XmlManager) context.getBean("xmlManager");
    }

    @Test
    public void testParseXml() throws Exception {
        MenuFoodListTuple sourceTuple = new MenuFoodListTuple();
        sourceTuple.setMenu(new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 6, 20)));
        sourceTuple.getFoodList().add(new Food(null,
                "Koprovka",
                BigDecimal.valueOf(89),
                "150g",
                LocalDate.of(2016,6, 20),
                null));
        sourceTuple.getFoodList().add(new Food(null,
                "Rajská",
                BigDecimal.valueOf(99),
                "150g",
                LocalDate.of(2016,6, 20),
                null));

        MenuFoodListTuple tuple = manager.parseXml("src/main/resources/test/testMenu.xml");
        assertThat(tuple.getMenu()).isEqualToComparingFieldByField(sourceTuple.getMenu());
        //BUG Food list must be compared manually
        //assertThat(tuple.getFoodList()).isEqualTo(sourceTuple.getFoodList()).usingFieldByFieldElementComparator();
    }

    @Test
    public void testGenerateXml() throws Exception {
        MenuFoodListTuple sourceTuple = new MenuFoodListTuple();
        sourceTuple.setMenu(new Menu(null, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 6, 20)));
        sourceTuple.getFoodList().add(new Food(null,
                "Koprovka",
                BigDecimal.valueOf(89),
                "150g",
                LocalDate.of(2016,6, 20),
                null));
        sourceTuple.getFoodList().add(new Food(null,
                "Rajská",
                BigDecimal.valueOf(99),
                "150g",
                LocalDate.of(2016,6, 20),
                null));

        manager.generateXml("src/main/resources/test/outputTestMenu.xml", sourceTuple);
        //Need to check manually
    }

    @Test
    public void testXsltGeneration(){
        manager.generateHtmlFromXml("src/main/resources/test/outputTestMenu.xml", "src/main/resources/test/output.html" );
    }
}
