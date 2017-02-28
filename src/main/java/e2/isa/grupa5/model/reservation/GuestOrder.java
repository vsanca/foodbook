package e2.isa.grupa5.model.reservation;

import javax.persistence.*;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.reservation.Reservation;
import e2.isa.grupa5.model.reservation.ServingItem;
import e2.isa.grupa5.model.restaurant.MenuItem;

/**
 * N ovih se vezuje za  tacno 1 gosta
 * sa tacno 1 rezervacijom
 *
 * @author Korisnik
 *
 */
@Entity
@Table(name = "guest_order")
public class GuestOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation")
	private Reservation reservation;
	
	@ManyToOne
	private MenuItem item;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest")
	private Guest guest;
	
	public GuestOrder(Reservation reservation, MenuItem menuItem, Guest guest){
		this.reservation = reservation;
		this.item = menuItem;
		this.guest = guest;
	}
	
	public GuestOrder(){
		
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

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	
	
}
