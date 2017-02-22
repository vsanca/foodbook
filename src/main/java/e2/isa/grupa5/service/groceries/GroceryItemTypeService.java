package e2.isa.grupa5.service.groceries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.groceries.GroceryItemType;
import e2.isa.grupa5.model.groceries.GroceryItemTypeDTO;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;

/**
 * Funkcionalnost 2.3:
 * - Objavljivanje ponude
 * 
 * @author Viktor
 *
 */
@Service
public class GroceryItemTypeService {

	@Autowired
	GroceryItemTypeRepository groceryItemTypeRepository;
	
	public GroceryItemType create(GroceryItemTypeDTO gitDTO) {
		GroceryItemType itemType = new GroceryItemType();
		itemType.setName(gitDTO.getName());
		
		try {
			itemType = groceryItemTypeRepository.save(itemType);
			return itemType;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
