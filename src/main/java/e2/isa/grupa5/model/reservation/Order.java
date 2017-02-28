package e2.isa.grupa5.model.reservation;

import javax.persistence.*;

import e2.isa.grupa5.model.users.Guest;

/**
 * N ovih se vezuje za  tacno 1 gosta
 * sa tacno 1 rezervacijom
 *
 * @author Korisnik
 *
 */
@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation")
	private Reservation reservation;
	
	@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "serving_item")
	private ServingItem servingItem;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest")
	private Guest guest;
	
	public Order(Reservation reservation, ServingItem servingItem, Guest guest){
		this.reservation = reservation;
		this.servingItem = servingItem;
		this.guest = guest;
	}
	
	public Order(){
		
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

	
	public ServingItem getServingItem() {
		return servingItem;
	}

	public void setServingItem(ServingItem servingItem) {
		this.servingItem = servingItem;
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
