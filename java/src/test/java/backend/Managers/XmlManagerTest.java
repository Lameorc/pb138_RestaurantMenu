package backend.Managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Vojta Podhajsky on 21.06.2016.
 */
public class XmlManagerTest {

    private XmlManager manager;

    @BeforeMethod
    public void setUp() throws Exception {
        manager = new XmlManager();
        manager.setXmlSchema("src/main/resources/xml/xml_scheme.xsd");
    }

    @Test
    public void testParseXml() throws Exception {

    }

    @Test
    public void testGenerateXml() throws Exception {

    }

    @Test
    public void testValidateXml() throws Exception {
        manager.validateXml("src/main/resources/xml/menu.xml");
    }

}
