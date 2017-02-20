package e2.isa.grupa5.repository.reservation;





import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.reservation.Reservation;



/**
 * 
 * @author Juri
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
}
