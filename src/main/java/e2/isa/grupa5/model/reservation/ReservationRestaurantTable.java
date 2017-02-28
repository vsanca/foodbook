package e2.isa.grupa5.model.reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import e2.isa.grupa5.model.restaurant.RestaurantTable;

@Entity
public class ReservationRestaurantTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation", nullable = false)
	private Reservation reservation;
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "table", nullable = false)
	private RestaurantTable table;
	
	public ReservationRestaurantTable(){
		
	}
	
	public ReservationRestaurantTable(Reservation reservation, RestaurantTable table){
		this.reservation = reservation;
		this.table = table;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public void setTable(RestaurantTable table) {
		this.table = table;
	}
	
	
}
