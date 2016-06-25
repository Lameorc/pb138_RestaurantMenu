package backend.xmlObjects;

import backend.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlAttribute(name="date")
    private Date date;

    @XmlAttribute(name="name")
    private String name;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
