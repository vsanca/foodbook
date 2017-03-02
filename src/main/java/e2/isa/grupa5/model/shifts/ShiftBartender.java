package e2.isa.grupa5.model.shifts;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import e2.isa.grupa5.model.users.Bartender;


/**
 * Entity for joining {@link Bartender} with {@link Shift}.
 * 
 * @author Boris
 *
 */
@Entity
@Table(name = "shift_bartender")
public class ShiftBartender implements Serializable{
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "bartender", nullable = false)
	private Bartender bartender;
	
	@ManyToOne
	@JoinColumn(name = "shift", nullable = false)
	private Shift shift;
	
	public ShiftBartender() {
		
	}

	public ShiftBartender(Bartender bartender, Shift shift) {
		this.bartender = bartender;
		this.shift = shift;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Bartender getBartender() {
		return bartender;
	}

	public void setBartender(Bartender bartender) {
		this.bartender = bartender;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	
	
}
