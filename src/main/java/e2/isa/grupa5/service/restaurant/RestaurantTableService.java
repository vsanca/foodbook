package e2.isa.grupa5.service.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.restaurant.RestaurantTableDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;

/**
 * Funkcionalnost 2.3:
 * - kreiranje i ažuriranje rasporeda sedenja u restoranu.
 * 
 * @author Viktor
 *
 */
@Service
public class RestaurantTableService {
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	@Autowired
	RestaurantAreaRepository restaurantAreaRepository;
	
	
	public RestaurantTable create(RestaurantTableDTO rtDTO) {
		
		RestaurantTable rt = new RestaurantTable();
		
		rt.setId(rtDTO.getId());
		rt.setName(rtDTO.getName());
		rt.setArea(restaurantAreaRepository.findById(rtDTO.getArea()));
		rt.setSeats(rtDTO.getSeats());
		rt.setFabricTable(rtDTO.getFabricTable());
		
		try {
			rt = restaurantTableRepository.save(rt);
			return rt;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<RestaurantTable> findAllByRestaurantId(long id) {
		return restaurantTableRepository.findByArea_Restaurant_Id(id);
	}
	
	public List<RestaurantTable> findAllByManagerId(long id) {
		RestaurantManager m = restaurantManagerRepository.findById(id);
		
		return restaurantTableRepository.findByArea_Restaurant_Id(m.getRestaurant().getId());
	}
	
	public void updateAllTables(List<RestaurantTableDTO> tDTOs) {
		List<RestaurantTable> tables = new ArrayList<RestaurantTable>();
		
		for (RestaurantTableDTO tDTO : tDTOs) {
			tables.add(create(tDTO));
		}
		
		try {
			restaurantTableRepository.save(tables);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
