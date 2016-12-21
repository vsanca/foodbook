package e2.isa.grupa5.model.users;

import e2.isa.grupa5.model.Reservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "guest")
public class Guest extends User implements Serializable {

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	/*@JoinTable(name = "Guest", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "user_id",
					nullable = false, updatable = false) })*/
	@JoinColumn(name = "id")
	private List<Guest> friends = new ArrayList<>();

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	//private List<Reservation> reservations = new ArrayList<>();


	public Guest() {
	}

	public Guest(User u) {
		super(u);
	}

	public List<Guest> getFriends() {
		return friends;
	}

	public void setFriends(List<Guest> friends) {
		this.friends = friends;
	}


	/*public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}*/

}

