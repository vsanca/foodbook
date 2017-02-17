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
	private Date from;
	
	@Column(name = "to_date", nullable = false)
	private Date to;
	
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

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
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
	
}
