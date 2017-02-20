package e2.isa.grupa5.model.grade;

import java.util.Date;

import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.Guest;

/**
 * {@link Grade} for use in communication (DTO).
 * 
 * @author Juri
 *
 */
public class GradeDTO {

	private long id;
	private Guest guest; 
	private Date date;
	private int mealGrade;
	private int bartenderGrade;
	private int atmosphereGrade;
	private int environmentGrade;
	private double restaurantGrade;
	private Reservation reservation;
	
	public GradeDTO(){
		
	}
	
	public GradeDTO(long id, Guest guest, Date date, int mealGrade, int bartenderGrade, int atmosphereGrade, int environmentGrade, double restraurantGrade, Reservation reservation){
		
		this.id = id;
		this.guest = guest;
		this.date = date;
		this.mealGrade = mealGrade;
		this.bartenderGrade = bartenderGrade;
		this.atmosphereGrade = atmosphereGrade;
		this.environmentGrade = environmentGrade;
		this.restaurantGrade = restraurantGrade;
		this.reservation = reservation;
		
	}
	
}
