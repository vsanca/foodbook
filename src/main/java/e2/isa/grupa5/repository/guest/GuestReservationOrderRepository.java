package e2.isa.grupa5.repository.guest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import e2.isa.grupa5.model.reservation.GuestReservationOrder;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.Guest;

@Repository
public interface GuestReservationOrderRepository extends JpaRepository<GuestReservationOrder, Long> {
	
	List<GuestReservationOrder> findByReservationAndGuest(Reservation reservation, Guest guest);

	List<GuestReservationOrder> findByReservation(Reservation reservation);
	
}

