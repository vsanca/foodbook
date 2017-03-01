package e2.isa.grupa5.model.reservation;

import e2.isa.grupa5.model.users.Guest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="InvitedToReservation")
public class InvitedToReservation implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invited_to_reservation_id")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private Guest guest;
	
	@JsonIgnore
	@ManyToOne
	private Reservation reservation; 
	
	/**
	 * 
	 */
	private boolean confirmed;

	public InvitedToReservation() {
		
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

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	

}
