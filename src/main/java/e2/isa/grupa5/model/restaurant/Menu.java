package e2.isa.grupa5.model.restaurant;

import javax.persistence.*;

/**
 * 
 * Restaurant menu.
 * 
 * @author Viktor
 *
 */

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    public Menu() {
    	
    }
    
    public Menu(Restaurant restaurant) {
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