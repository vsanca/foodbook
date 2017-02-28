package e2.isa.grupa5.model.reservation;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.reservation.GuestOrder;
import e2.isa.grupa5.model.reservation.ReservationRestaurantTable;

@Entity
@Table(name="reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private Long id; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant", nullable = false)
	private Restaurant restaurant;
	
	@Column(name="reservation_start")
	private Date terminOd; 
	
	@Column(name="reservation_end")
	private Date terminDo; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest", nullable = false)
	private Guest guest;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reserved_tables")
	private List<ReservationRestaurantTable> reservedTables = new ArrayList<>();

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_orders")
	private List<GuestOrder> orders = new ArrayList<>();
	
	
	public Reservation(Restaurant restaurant, Date terminOd, Date terminDo, Guest guest, List<ReservationRestaurantTable> reservedTables, List<GuestOrder> orders) {
		this.restaurant = restaurant;
		this.terminOd = terminOd;
		this.terminDo = terminDo;
		this.guest = guest;
		this.reservedTables = reservedTables;
		this.orders = orders;
	}
	
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

	public List<GuestOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
	}
	
	
	
}
