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

import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.users.Waiter;

@Entity
public class ServingItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item")
	private MenuItem menuItem;
	
	@Column(name="is_accepted")
	private boolean isAccepted;
	
	@Column(name="is_created")
	private boolean isCreated;
	
	@Column(name="is_delivered")
	private boolean isDelivered;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "waiter")
	private Waiter waiter;
	
	
	
	public ServingItem(){
		
	}

	public ServingItem(MenuItem menuItem, boolean isAccepted, boolean isCreated, boolean isDelivered, Waiter waiter){
		this.menuItem = menuItem;
		this.isAccepted = isAccepted;
		this.isCreated = isCreated;
		this.isDelivered = isDelivered;
		this.waiter = waiter;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
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
	
	
	
	
}
