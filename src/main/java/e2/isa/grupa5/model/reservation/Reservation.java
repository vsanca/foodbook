package e2.isa.grupa5.model.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.users.Guest;

@Entity
@Table(name="Reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private Long id; 
	
	@ManyToOne
	private Restaurant restaurant;
	
	@Column(name="reservation_start")
	private Date terminOd; 
	
	@Column(name="reservation_end")
	private Date terminDo; 
	
	@ManyToOne
	private Guest guest;
	
	@JsonIgnore
	@OneToMany
	private List<ReservationRestaurantTable> reservedTables = new ArrayList<>();

	@OneToMany
	private List<GuestReservationOrder> orders = new ArrayList<>();
	
	
	public Reservation() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getTerminOd() {
		return terminOd;
	}

	public void setTerminOd(Date terminOd) {
		this.terminOd = terminOd;
	}

	public Date getTerminDo() {
		return terminDo;
	}

	public void setTerminDo(Date terminDo) {
		this.terminDo = terminDo;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<ReservationRestaurantTable> getReservedTables() {
		return reservedTables;
	}

	public void setReservedTables(List<ReservationRestaurantTable> reservedTables) {
		this.reservedTables = reservedTables;
	}

	public List<GuestReservationOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuestReservationOrder> orders) {
		this.orders = orders;
	}
	
	
	
}
