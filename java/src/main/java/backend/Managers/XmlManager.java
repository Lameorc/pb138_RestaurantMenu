package backend.Managers;

import backend.utils.Exceptions.XmlValidationException;
import backend.utils.XmlOutputObject;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Vojta Podhajsky on 21.06.2016.
 * Due to how shitty the sax parser file opener is the filepath needs to be from project root
 */
public class XmlManager {

    private String xmlSchema;

    public XmlOutputObject parseXml(String xmlFile){
        XmlOutputObject object = new XmlOutputObject();
        object.setFood(null);
        object.setMenu(null);
        if(validateXml(xmlFile)){
            throw new XmlValidationException("Xml file is not valid against the schema");
        }

        return object;
    }

    public void generateXml(String xmlFile){

    }

    public boolean validateXml(String xmlFile){
        try{
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xmlSchema));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void setXmlSchema(String xmlSchema) {
        this.xmlSchema = xmlSchema;
    }

    public String getXmlSchema() {
        return xmlSchema;
    }
}
