package e2.isa.grupa5.model.reservation;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.reservation.Reservation;

import javax.persistence.*;

@Entity
@Table(name="invited_to_reservation")
public class InvitedToReservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invited_to_reservation_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest")
	private Guest guest;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation", nullable = false)
	private Reservation reservation; 

	public InvitedToReservation() {
		
	}
	
	public InvitedToReservation(Guest guest,Reservation reservation) {
		this.guest = guest;
		this.reservation = reservation;
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

}
