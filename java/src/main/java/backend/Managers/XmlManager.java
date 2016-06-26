package backend.Managers;

import backend.entities.Food;
import backend.entities.Menu;
import backend.utils.MenuFoodListTuple;
import backend.xmlObjects.Day;
import backend.xmlObjects.Meal;
import backend.xmlObjects.Menus;
import org.springframework.integration.xml.transformer.XsltPayloadTransformer;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 21.06.2016.
 * Due to how shitty the sax parser file opener is the filepath needs to be from project root
 */
public class XmlManager {

    private String xmlSchema;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private String xsltTransformation;

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


    public MenuFoodListTuple parseXml(String xmlFile) throws IOException {
        FileInputStream fis = null;
        Menus menus = null;
        try {
            fis = new FileInputStream(xmlFile);
            menus = (Menus) unmarshaller.unmarshal(new StreamSource(fis));
        } finally {
            fis.close();
        }
        return parseMenusToFoodListTuple(menus);
    }

    public void generateXml(String xmlFile, MenuFoodListTuple tuple) throws IOException, DatatypeConfigurationException {
        FileOutputStream fos = null;
        Menus menus = parseMenuFoodListTupleToMenus(tuple);
        try {
            fos = new FileOutputStream(xmlFile);
            marshaller.marshal(menus, new StreamResult(fos));
        } finally {
            fos.close();
        }
    }

    public void generateHtmlFromXml(String xmlFile, String htmlFile) {
        try {
            parseXml(xmlFile);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer xsltProc = tf.newTransformer(
                    new StreamSource(new File(xsltTransformation)));

            xsltProc.transform(
                    new StreamSource(new File(xmlFile)),
                    new StreamResult(new File(htmlFile)));

        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            System.err.println("Unable to transform XML to HTML");
        }
    }


    private Menus parseMenuFoodListTupleToMenus(MenuFoodListTuple tuple) throws DatatypeConfigurationException {
        Menus menus = new Menus();
        List<Food> foods = tuple.getFoodList();
        for(Food f : foods){
            Meal meal = new Meal();
            Day day = menus.getDayWithDate(f.getDate());
            if(day == null){
                day = new Day();
                day.setName(translateDayToCzech(f.getDate().getDayOfWeek()));
                XMLGregorianCalendar xcal = DatatypeFactory.newInstance().
                        newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(f.getDate())));
                day.setDate(xcal);
            }
            meal.setName(f.getName());
            meal.setPrice(f.getPrice());
            meal.setWeight(f.getWeight());
            meal.setNumber(day.getMeals().size() + 1);
            day.getMeals().add(meal);
            menus.getDays().add(day);
        }
        return menus;
    }

    private MenuFoodListTuple parseMenusToFoodListTuple(Menus menus){
        MenuFoodListTuple tuple = new MenuFoodListTuple();
        LocalDate startDate = null;
        LocalDate endDate = null;
        for(Day d: menus.getDays()){
            LocalDate date = d.getDateAsCalendar().toGregorianCalendar().toZonedDateTime().toLocalDate();
            if(startDate == null){
                startDate = date;
            }
            else if(startDate.isAfter(date)){
                startDate = date;
            }

            if(endDate == null){
                endDate = date;
            }
            else if(endDate.isBefore(date)){
                endDate = date;
            }

            for(Meal m: d.getMeals()){
                Food f = new Food(null, m.getName(), m.getPrice(), m.getWeight(), date, null);
                tuple.getFoodList().add(f);
            }
        }
        tuple.setMenu(new Menu(null, startDate, endDate));
        return tuple;
    }

    private String translateDayToCzech(DayOfWeek day){
        switch (day){
            case MONDAY: return "Pondělí";
            case TUESDAY: return "Úterý";
            case WEDNESDAY: return "Středa";
            case THURSDAY: return "Čtvrtek";
            case FRIDAY: return "Pátek";
            case SATURDAY: return "Sobota";
            case SUNDAY: return "Neděle";
        }
        return "Neznamý";
    }

    public String getXsltTransformation() {
        return xsltTransformation;
    }

    public void setXsltTransformation(String xsltTransformation) {
        this.xsltTransformation = xsltTransformation;
    }
}
