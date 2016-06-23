package backend.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by Vojta Podhajsky on 12.06.2016.
 */
public class Menu {
    private Long id;
    @DateTimeFormat (pattern="dd. MM. YYYY")
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Menu(Long id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
