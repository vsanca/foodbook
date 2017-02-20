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
	private int waiterGrade;
	private int atmosphereGrade;
	private int environmentGrade;
	private double restaurantGrade;
	private Reservation reservation;
	
	public GradeDTO(){
		
	}
	
	public GradeDTO(long id, Guest guest, Date date, int mealGrade, int waiterGrade, int atmosphereGrade, int environmentGrade, double restraurantGrade, Reservation reservation){
		
		this.id = id;
		this.guest = guest;
		this.date = date;
		this.mealGrade = mealGrade;
		this.waiterGrade = waiterGrade;
		this.atmosphereGrade = atmosphereGrade;
		this.environmentGrade = environmentGrade;
		this.restaurantGrade = restraurantGrade;
		this.reservation = reservation;
		
	}
	
	public GradeDTO(Grade g){
		this.id = g.getId();
		this.guest = g.getGuest();
		this.date = g.getDate();
		this.mealGrade = g.getMealGrade();
		this.waiterGrade = g.getWaiterGrade();
		this.atmosphereGrade = g.getAtmosphereGrade();
		this.environmentGrade = g.getEnvironmentGrade();
		this.restaurantGrade = g.getRestaurantGrade();
		this.reservation = g.getReservation();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMealGrade() {
		return mealGrade;
	}

	public void setMealGrade(int mealGrade) {
		this.mealGrade = mealGrade;
	}

	public int getBartenderGrade() {
		return waiterGrade;
	}

	public void setBartenderGrade(int waiterGrade) {
		this.waiterGrade = waiterGrade;
	}

	public int getAtmosphereGrade() {
		return atmosphereGrade;
	}

	public void setAtmosphereGrade(int atmosphereGrade) {
		this.atmosphereGrade = atmosphereGrade;
	}

	public int getEnvironmentGrade() {
		return environmentGrade;
	}

	public void setEnvironmentGrade(int environmentGrade) {
		this.environmentGrade = environmentGrade;
	}

	public double getRestaurantGrade() {
		return restaurantGrade;
	}

	public void setRestaurantGrade(double restaurantGrade) {
		this.restaurantGrade = restaurantGrade;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	
	
}
