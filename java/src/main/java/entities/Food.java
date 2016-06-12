package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class Food {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private LocalDate date;
    private Long menu_id;
}
