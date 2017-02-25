package e2.isa.grupa5.repository.grade;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.UserRoles;


/**
 * 
 * @author Boris
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {
	
	public Grade findById(long id);
	public List<Grade> findByReservation_id(long id);
	public Grade findByReservation_idAndGuest_id(long reservationId, long guestId);
	
}
