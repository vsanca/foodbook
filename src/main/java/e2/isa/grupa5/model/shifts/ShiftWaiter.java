package e2.isa.grupa5.model.shifts;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.users.Waiter;

/**
 * Entity for joining {@link Waiter} with {@link Shift} and assigned {@link RestaurantArea}s.
 * 
 * Funkcionalnost 2.3:
 * - definisanje smena konobarima
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "shift_waiter")
public class ShiftWaiter {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "waiter", nullable = false)
	private Waiter waiter;
	
	@ManyToOne
	@JoinColumn(name = "shift", nullable = false)
	private Shift shift;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "areas", joinColumns = {@JoinColumn(name = "shift_id", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "restaurant_area_id", nullable = false)})
	private Set<RestaurantArea> areas;
	
	public ShiftWaiter() {

	}

	public ShiftWaiter(Waiter waiter, Shift shift) {
		this.waiter = waiter;
		this.shift = shift;
		this.areas = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public Set<RestaurantArea> getAreas() {
		return areas;
	}

	public void setAreas(Set<RestaurantArea> areas) {
		this.areas = areas;
	}

}
