package backend.xmlObjects;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Created by Vojta Podhajsky on 25.06.2016.
 */
@XmlRootElement(name="meal")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meal {
    @XmlElement(name="weight")
    private String weight;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="price")
    private BigDecimal price;
    @XmlAttribute(name="number")
    private Integer number;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
