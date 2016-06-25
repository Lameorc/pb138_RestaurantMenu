package backend.Managers;

import backend.entities.Menu;
import backend.xmlObjects.Menus;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        Menus menus= manager.parseXml("src/main/resources/xml/menu.xml");
        assertThat(menus).isNotNull();
    }

    @Test
    public void testGenerateXml() throws Exception {
        Menus list = manager.parseXml("src/main/resources/xml/menu.xml");
        manager.generateXml("src/main/resources/test/testMenu.xml", list);
        Menus newList = manager.parseXml("src/main/resources/test/testMenu.xml");
        assertThat(list).isEqualToComparingFieldByField(newList);

    }
}
