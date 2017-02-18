package e2.isa.grupa5.model.groceries;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.Restaurant;

@Entity
@Table(name = "groceries")
public class Groceries {
	
	@Id
	@GeneratedValue
	private long id;
	
	// PAZITI NA KLJUČNE REČI SQL (from, to...)!!! POLJA SE NE SMEJU TAKO ZVATI!
	@Column(name = "from_date", nullable = false)
	private Date from_date;
	
	@Column(name = "to_date", nullable = false)
	private Date to_date;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;
	
	public Groceries() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	
}
