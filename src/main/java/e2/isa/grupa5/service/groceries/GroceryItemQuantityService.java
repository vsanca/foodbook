package e2.isa.grupa5.service.groceries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemQtyDTO;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;

/**
 * Funkcionalnost 2.3:
 * - Objavljivanje ponude
 * 
 * @author Viktor
 *
 */
@Service
public class GroceryItemQuantityService {

	@Autowired
	GroceryItemQuantityRepository groceryItemQuantityRepository;
	
	
	public GroceryItemQty create(GroceryItemQtyDTO giqDTO) {
		GroceryItemQty qty = new GroceryItemQty();
		qty.setName(giqDTO.getName());
		
		try {
			qty = groceryItemQuantityRepository.save(qty);
			return qty;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
