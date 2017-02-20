package e2.isa.grupa5.model.shifts;

import java.util.List;

/**
 * {@link ShiftWaiter} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - definisanje smena
 * 
 * @author Viktor
 *
 */
public class ShiftWaiterDTO extends ShiftDTO{
	
	private List<Integer> areas;

	public ShiftWaiterDTO() {
		super();
	}

	public List<Integer> getAreas() {
		return areas;
	}

	public void setAreas(List<Integer> areas) {
		this.areas = areas;
	}
	
}
