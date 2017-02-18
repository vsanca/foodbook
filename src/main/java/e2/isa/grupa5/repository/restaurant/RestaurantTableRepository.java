package e2.isa.grupa5.repository.restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantTable;

/**
 * 
 * @author Viktor
 *
 */
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

	public RestaurantTable findById(long id);
	public List<RestaurantTable> findByArea_Restaurant_Id(long id);
	public List<RestaurantTable> findByArea(RestaurantArea area);
	
}
