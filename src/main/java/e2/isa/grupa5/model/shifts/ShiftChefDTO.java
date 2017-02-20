package e2.isa.grupa5.model.shifts;

import e2.isa.grupa5.model.users.Chef;

public class ShiftChefDTO {
	
	private long id;
	private Chef chef;
	private Shift shift;
	
	public ShiftChefDTO(){
		
	}
	
	public ShiftChefDTO(Chef chef, Shift shift) {
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
