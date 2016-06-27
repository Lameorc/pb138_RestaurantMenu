package backend.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class Food {
    private Long id;
    private String name;
    private BigDecimal price;
    private String weight;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menu_id) {
        this.menuId = menu_id;
    }

    public Food(Long id, String name, BigDecimal price, String weight, LocalDate date, Long menuId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.date = date;
        this.menuId = menuId;
    }

    public Food() {

    }
}
