package backend.entities;

/**
 * Created by Vojta Podhajsky on 24.06.2016.
 */
public class Reservation {
    private String userName;
    private Long foodId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Reservation(String userName, Long foodId) {
        this.userName = userName;
        this.foodId = foodId;
    }
}
