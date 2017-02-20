package e2.isa.grupa5.model.grade;

import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.users.Guest;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="grade")
public class Grade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grade_id")
	private Long id;

	@ManyToOne
	private Guest guest;  

	@Column(name="grade_date")
	private Date date;
	
	@Column(name="meal_grade")
	private int mealGrade;
	
	@Column(name="bartender_grade")
	private int bartenderGrade;
	
	@Column(name="atmosphere_grade")
	private int atmosphereGrade;
	
	@Column(name="environment_grade")
	private int environmentGrade;
	
	@Column(name="restaurant_grade")
	private double restaurantGrade;

	@ManyToOne
	private Reservation reservation;

	public Grade() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return bartenderGrade;
	}

	public void setBartenderGrade(int bartenderGrade) {
		this.bartenderGrade = bartenderGrade;
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
