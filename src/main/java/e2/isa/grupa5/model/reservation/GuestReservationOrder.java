package e2.isa.grupa5.model.reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.model.users.Waiter;

/**
 * N ovih se vezuje za  tacno 1 gosta
 * sa tacno 1 rezervacijom
 *
 * @author Korisnik
 *
 */
@Entity
public class GuestReservationOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	Reservation reservation;
	
	@ManyToOne
	MenuItem item;
	
	@ManyToOne
	Guest guest;
	
	@ManyToOne
	Waiter waiter;
	
	@ManyToOne
	Chef chef;
	
	@Column(name="be_prepared")
	boolean bePrepared;
	
	@Column(name="is_accepted")
	boolean isAccepted;
	
	@Column(name="is_created")
	boolean isCreated;
	
	@Column(name="is_delivered")
	boolean isDelivered;
	
	

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

	public boolean isBePrepared() {
		return bePrepared;
	}

	public void setBePrepared(boolean bePrepared) {
		this.bePrepared = bePrepared;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public boolean isCreated() {
		return isCreated;
	}

	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	
	
}
