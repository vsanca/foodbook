package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;

/**
 * {@link RestaurantTable} for use in communication (DTO).
 * 
 * Funkcionalnost 2.3:
 * - Uređuje konfiguraciju sedenja u restoranu sa naznakom segmenata u restoranu
 * 
 * @author Viktor
 *
 */
public class RestaurantTableDTO implements Serializable{
	
	private long id;
	private String name;
	private int seats;
	private long area;
	private String fabricTable;
	
	public RestaurantTableDTO() {
		
	}

	public RestaurantTableDTO(long id, String name, int seats, long area, String fabricTable) {
		this.id = id;
		this.name = name;
		this.seats = seats;
		this.area = area;
		this.fabricTable = fabricTable;
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

	public long getArea() {
		return area;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public String getFabricTable() {
		return fabricTable;
	}

	public void setFabricTable(String fabricTable) {
		this.fabricTable = fabricTable;
	}
	
}
