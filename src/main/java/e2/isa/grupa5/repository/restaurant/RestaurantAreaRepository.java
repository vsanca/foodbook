package e2.isa.grupa5.repository.restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.RestaurantArea;

/**
 * 
 * @author Viktor
 *
 */
public interface RestaurantAreaRepository extends JpaRepository<RestaurantArea, Long>{
	
	public RestaurantArea findByName(String name);
	public RestaurantArea findById(long id);
	public List<RestaurantArea> findByRestaurant_Id(long id);
	
}
