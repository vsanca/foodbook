package e2.isa.grupa5.model.restaurant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A table in {@link RestaurantArea} in {@link Restaurant}.
 * 
 * Funkcionalnost 2.3:
 * - Uređuje konfiguraciju sedenja u restoranu sa naznakom segmenata u restoranu
 * 
 * @author Viktor
 *
 */
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable implements Serializable{
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "seats", nullable = false)
	private int seats;
	
	@Column(name = "fabric", length = 3000)
	private String fabricTable;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", nullable = false)
    private RestaurantArea area;
	
	public RestaurantTable() {
		
	}
	
	public RestaurantTable(String name, int seats, RestaurantArea area, String fabric) {
		this.name = name;
		this.seats = seats;
		this.area = area;
		this.fabricTable = fabric;
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

	public RestaurantArea getArea() {
		return area;
	}

	public void setArea(RestaurantArea area) {
		this.area = area;
	}

	public String getFabricTable() {
		return fabricTable;
	}

	public void setFabricTable(String fabricTable) {
		this.fabricTable = fabricTable;
	}
	
}
