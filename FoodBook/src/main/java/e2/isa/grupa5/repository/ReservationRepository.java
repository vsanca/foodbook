package e2.isa.grupa5.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Guest;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByGuest(Guest guest);
	List<Reservation> findById(Long id);
	List<Reservation> findByRestaurant(Restaurant restaurant);
	List<Reservation> findByTerminDo(Date termindo);
	List<Reservation> findByTerminOd(Date terminod);
	Long countByGuest(Guest guest);
	
}
