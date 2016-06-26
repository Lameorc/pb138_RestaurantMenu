package backend.Managers;

import backend.entities.Food;
import backend.entities.Reservation;

import java.util.List;

/**
 * Created by Vojta Podhajsky on 24.06.2016.
 */
public interface ReservationManager {
    List<Reservation> getAllReservations();
    void reserveFoodByUser(Long foodId, String userName);
    List<Food> getFoodReservedByUser(String userName);
}
