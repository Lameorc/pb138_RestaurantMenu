package backend.Managers;

import backend.xmlObjects.Menus;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Vojta Podhajsky on 21.06.2016.
 * Due to how shitty the sax parser file opener is the filepath needs to be from project root
 */
public class XmlManager {

    private String xmlSchema;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }


    public void setXmlSchema(String xmlSchema) {
        this.xmlSchema = xmlSchema;
    }

    public String getXmlSchema() {
        return xmlSchema;
    }


    public Menus parseXml(String xmlFile) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(xmlFile);
            return (Menus) unmarshaller.unmarshal(new StreamSource(fis));
        } finally {
            fis.close();
        }
    }

    public void generateXml(String xmlFile, Menus menus) throws IOException{
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(xmlFile);
            marshaller.marshal(menus, new StreamResult(fos));
        } finally {
            fos.close();
        }
    }
}
