package e2.isa.grupa5.repository;

import e2.isa.grupa5.model.Reservation;
import e2.isa.grupa5.model.Restaurant;
import e2.isa.grupa5.model.users.Guest;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.lang.Long;
import java.util.Date;

/*
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByGuest(Guest guest);
	List<Reservation> findById(Long id);
	List<Reservation> findByRestaurant(Restaurant restaurant);
	List<Reservation> findByTerminDo(Date termindo);
	List<Reservation> findByTerminOd(Date terminod);
}
*/