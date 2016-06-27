package backend.Managers;

import backend.entities.Food;
import backend.entities.Reservation;

import java.util.List;

/**
 * Interface for manager which allows food reservations by users
 * Created by Vojta Podhajsky on 24.06.2016.
 */
public interface ReservationManager {
    /**
     * Gets all reservations 
     * @return List of reservations, empty if none exist
     */
    List<Reservation> getAllReservations();
    /**
     * Reserves food based on {@link Food.getId()} and username. Both of these must be in database
     * @param foodId food to reserve
     * @param userName user which wants the reservation
     */
    void reserveFoodByUser(Long foodId, String userName);
    /**
     * Cancel reservation. Reservation must exist
     * @param foodId food which was reserved
     * @param userName user who reserved the food
     */
    void cancelReservationByUser(Long foodId, String userName);
    /**
     * Gets all reservations by a specific user
     * @param userName user for whom we wish to know the reservations
     * @return List of {@link Food}, empty if no food is reserved by the user.
     */
    List<Food> getFoodReservedByUser(String userName);
}
