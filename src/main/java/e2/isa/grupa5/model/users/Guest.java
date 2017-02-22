package e2.isa.grupa5.model.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import e2.isa.grupa5.model.reservation.Reservation;

@Entity
@Table(name = "guest")
public class Guest extends User implements Serializable {

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	 @JoinTable(
	   name = "guestfriends", 
	   joinColumns = @JoinColumn(name = "guest_id"), 
	   inverseJoinColumns = @JoinColumn(name = "friend_id")
	 )
	private List<Guest> friends = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Reservation> reservations = new ArrayList<>();


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


	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}

