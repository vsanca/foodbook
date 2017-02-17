package e2.isa.grupa5.model.restaurant;

import javax.persistence.*;

/**
 * 
 * Restaurant beverage menu.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name = "drink_menu")
public class DrinkMenu {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    public DrinkMenu() {
    	
    }
    
    public DrinkMenu(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
