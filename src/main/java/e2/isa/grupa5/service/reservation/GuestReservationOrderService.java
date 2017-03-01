package e2.isa.grupa5.service.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.reservation.GuestReservationOrder;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.repository.reservation.GuestReservationOrderRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;

@Service
public class GuestReservationOrderService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	GuestReservationOrderRepository guestReservationOrderRepository;
	
	public List<GuestReservationOrder> forChefReturnAllUnfinishedOrders(Chef ch){
		Restaurant r = ch.getRestaurant();
		
		List<Reservation> allReservations = reservationRepository.findByRestaurant(r);
		if(allReservations == null){
        	return null;
        }
		else{
			List<GuestReservationOrder> allOrders = new ArrayList<GuestReservationOrder>();
        	for(Reservation r1 : allReservations){
        		allOrders.addAll(guestReservationOrderRepository.findByReservation_Id(r1.getId()));
        	}
        	if(allOrders.isEmpty()){
        		return null;
        	}
        	else{
        		List<GuestReservationOrder> temp = new ArrayList<GuestReservationOrder>();
        		for(GuestReservationOrder order: allOrders){
        			if(!order.isCreated()){
        				temp.add(order);
        			}
        		}
        		if(temp.isEmpty()){
        			return null;
        		}
        		else{
        			List<GuestReservationOrder> myOders = new ArrayList<GuestReservationOrder>();
        			for(GuestReservationOrder o : temp){
        				if(o.getItem().getItem().getItemType().getName().equals("FOOD")){
        					myOders.add(o);
        				}
        			}
        			if(myOders.isEmpty()){
        				return null;
        			}
        			else{
        				return myOders;
        			}
        		}
        	}
		}
		
		
		
	}
}
