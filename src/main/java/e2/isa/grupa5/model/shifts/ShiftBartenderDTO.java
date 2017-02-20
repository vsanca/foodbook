package e2.isa.grupa5.model.shifts;

import e2.isa.grupa5.model.users.Bartender;

public class ShiftBartenderDTO {

	private long id;
	private Bartender bartender;
	private Shift shift;
	
	
	public ShiftBartenderDTO(){
		
	}
	
	public ShiftBartenderDTO(long id, Bartender bartender, Shift shift) {
		
		this.id = id;
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
