package e2.isa.grupa5.service.grade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.grade.Grade;
import e2.isa.grupa5.model.grade.GradeDTO;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.grade.GradeRepository;
import e2.isa.grupa5.repository.guest.ReservationRepository;

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
	
	
	/**
	 * Helper method for setting possible variable attributes.
	 * 
	 * @param g
	 * @param gDTO
	 */
	public void setVariableAttributes(Grade g, GradeDTO gDTO) {
		g.setId(gDTO.getId());
		g.setGuest(gDTO.getGuest());
		g.setDate(gDTO.getDate());
		g.setMealGrade(gDTO.getMealGrade());
		g.setWaiterGrade(gDTO.getWaiterGrade());
		g.setAtmosphereGrade(gDTO.getAtmosphereGrade());
		g.setEnvironmentGrade(gDTO.getEnvironmentGrade());
		g.setRestaurantGrade(gDTO.getRestaurantGrade());
		g.setReservation(gDTO.getReservation());
		g.setRated(gDTO.isRated());
	}
	
	
	/**
	 * Vraca sve ocene koje pripadaju prosledjenom restoranu
	 * 
	 * @author Boris
	 * 
	 * @param Restaurant
	 * @return List<Grade> 
	 */
	public List<GradeDTO> getGradesForRestaurant(Restaurant r) {
		
		
		List<Grade> myGrades = new ArrayList<Grade>();
		List<GradeDTO> myGradesDTO = new ArrayList<GradeDTO>();
		
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
			
			for(Grade g : myGrades){
				GradeDTO myDTO = new GradeDTO(g);
				myGradesDTO.add(myDTO);
			}
			
			return myGradesDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
