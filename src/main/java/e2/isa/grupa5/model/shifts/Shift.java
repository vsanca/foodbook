package e2.isa.grupa5.model.shifts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.Waiter;

/**
 * General shift description for all of the workers: {@link Chef}, {@link Waiter}, {@link Bartender}.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "shift")
public class Shift {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "day", nullable = false)
	private Date day;
	
	@Column(name = "start_time", nullable = false)
	private String startTime;
	
	@Column(name = "end_time", nullable = false)
	private String endTime;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "restaurant", nullable = false)
	private Restaurant restaurant;
	
	public Shift() {
		super();
	}

	public Shift(Date day, String startTime, String endTime, boolean active, Restaurant restaurant) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.active = active;
		this.restaurant = restaurant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
