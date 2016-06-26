package backend.xmlObjects;

import backend.entities.Food;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 25.06.2016.
 */
@XmlRootElement(name = "menus")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menus {
    public Menus() {
        days = new ArrayList<>();
    }

    @XmlElement(name="day")
    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public Day getDayWithDate(LocalDate date) {
        for(Day d :days ){
            if( d.getDateAsCalendar().toGregorianCalendar().toZonedDateTime().toLocalDate().isEqual(date)){
                return d;
            }
        }
        return null;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
