package e2.isa.grupa5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import e2.isa.grupa5.model.reservation.InvitedToReservation;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.Guest;

@Repository
public interface InvitedToReservationRepository extends JpaRepository<InvitedToReservation, Long> {

	List<InvitedToReservation> findByGuest(Guest guest);
	List<InvitedToReservation> findById(Long id);
	List<InvitedToReservation> findByReservation(Reservation reservation);
	Long countByGuest(Guest guest);
}
