package backend.xmlObjects;

import backend.entities.Food;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Vojta Podhajsky on 25.06.2016.
 */
@XmlRootElement(name = "menus")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menus {
    @XmlElement(name="day")
    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
