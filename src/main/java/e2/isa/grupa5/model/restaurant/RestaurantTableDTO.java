package e2.isa.grupa5.model.restaurant;


/**
 * {@link RestaurantTable} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - Uređuje konfiguraciju sedenja u restoranu sa naznakom segmenata u restoranu
 * 
 * @author Viktor
 *
 */
public class RestaurantTableDTO {
	
	private long id;
	private String name;
	private int seats;
	private long areaId;
	
	public RestaurantTableDTO() {
		
	}

	public RestaurantTableDTO(long id, String name, int seats, long areaId) {
		this.id = id;
		this.name = name;
		this.seats = seats;
		this.areaId = areaId;
	}
	
	public RestaurantTableDTO(RestaurantTable table) {
		this.id = table.getId();
		this.name = table.getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}
	
}
