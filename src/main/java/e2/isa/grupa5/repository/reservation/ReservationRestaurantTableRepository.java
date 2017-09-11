package e2.isa.grupa5.repository.reservation;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.reservation.ReservationRestaurantTable;

/**
 * 
 * @author Korisnik
 *
 */
@Repository
public interface ReservationRestaurantTableRepository  extends JpaRepository<ReservationRestaurantTable, Long> {

	List<ReservationRestaurantTable> findByReservation(Reservation reservation);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	ReservationRestaurantTable save(ReservationRestaurantTable reservationTable);
}
