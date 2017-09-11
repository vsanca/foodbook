package e2.isa.grupa5.model.reservation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import e2.isa.grupa5.model.restaurant.RestaurantTable;

@Entity
public class ReservationRestaurantTable implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@JsonIgnore
	@OneToOne
	Reservation reservation;
	
	@JsonIgnore
	@OneToOne
	RestaurantTable myTable;
	
//	@Version
    private int version;
	
	public ReservationRestaurantTable() {}
	
	
	
	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
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
		return myTable;
	}

	public void setTable(RestaurantTable table) {
		this.myTable = table;
	}
	
	
}
