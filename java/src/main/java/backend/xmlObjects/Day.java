package backend.xmlObjects;

import backend.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 25.06.2016.
 */
@XmlRootElement(name="day")
@XmlAccessorType(XmlAccessType.FIELD)
public class Day {
    @XmlElement(name="meal")
    private List<Meal> meals;

    @XmlAttribute(name="date")
    @XmlSchemaType(name="date")
    private XMLGregorianCalendar date;

    @XmlAttribute(name="name")
    private String name;

    public Day() {
        meals = new ArrayList<>();
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public String getDate() {
        return date.toXMLFormat();
    }

    public XMLGregorianCalendar getDateAsCalendar(){
        return date;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
