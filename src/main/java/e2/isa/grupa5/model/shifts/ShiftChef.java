package e2.isa.grupa5.model.shifts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.users.Chef;

/**
 * Entity for joining {@link Chef} with {@link Shift}.
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "shift_chef")
public class ShiftChef {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "chef", nullable = false)
	private Chef chef;
	
	@ManyToOne
	@JoinColumn(name = "shift", nullable = false)
	private Shift shift;
	
	public ShiftChef() {
		
	}

	public ShiftChef(Chef chef, Shift shift) {
		this.chef = chef;
		this.shift = shift;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}
	
}
