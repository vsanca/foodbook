package e2.isa.grupa5.repository.guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import e2.isa.grupa5.model.reservation.GuestReservationOrder;

@Repository
public interface GuestReservationOrderRepository extends JpaRepository<GuestReservationOrder, Long> {
	
	
}

