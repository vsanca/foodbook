package e2.isa.grupa5.service.grade;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.reservation.ReservationRepository;

/**
 * 
 * @author Boris
 * 
 */
@Service
public class GradeService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	// Za dat restoran vraca sve ocene
	public List<Grade> getGradesForRestaurant(Restaurant r) {
		
		
		List<Grade> myGrades = new ArrayList<Grade>();
		
		
		List<Reservation> allReservation = reservationRepository.findAll();
		List<Reservation> myReservations = new ArrayList<Reservation>();
		
		
		
		
		try {
			// za dat restoran vraca sve njegove rezervacije
			for(Reservation r1 : allReservation){
				if(r1.getRestaurant().getId() == r.getId()){
					myReservations.add(r1);
				}
			}
			// za sve rezervacije restorana, vraca sve njegove ocene
			for(Reservation r2 : myReservations){
				myGrades.addAll(gradeRepository.findByReservation_id(r2.getId()));
			}
			return myGrades;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
