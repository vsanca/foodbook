package e2.isa.grupa5.service.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.reservation.GuestReservationOrder;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;
import e2.isa.grupa5.rest.dto.guest.GuestReservationOrderDTO;

@Service
public class GuestReservationOrderService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	GuestReservationOrderRepository guestReservationOrderRepository;
	
	
		
}
