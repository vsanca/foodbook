package e2.isa.grupa5.service.groceries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceryItem;
import e2.isa.grupa5.model.groceries.GroceryItemDTO;
import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemType;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;

/**
 * 
 * @author Viktor
 *
 */
@Service
public class GroceryItemService {
	
	@Autowired
	GroceriesRepository groceriesRepository;
	
	@Autowired
	GroceryItemRepository groceryItemRepository;
	
	@Autowired
	GroceryItemTypeRepository groceryItemTypeRepository;
	
	@Autowired
	GroceryItemQuantityRepository groceryItemQtyRepository;
	
	
	public GroceryItem addItem(GroceryItemDTO giDTO) {
		
		GroceryItem gi = new GroceryItem();
		
		Groceries g = groceriesRepository.findById(giDTO.getGroceriesId());
		GroceryItemType type = groceryItemTypeRepository.findById(giDTO.getGroceriesItemType());
		GroceryItemQty qty = groceryItemQtyRepository.findById(giDTO.getGroceriesItemQty());		
		
		gi.setName(giDTO.getName());
		gi.setQuantity(giDTO.getQuantity());
		gi.setGroceries(g);
		gi.setGroceryItemType(type);
		gi.setGroceryItemQty(qty);
		
		try {
			gi = groceryItemRepository.save(gi);
			return gi;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<GroceryItem> getItemsByGroceriesId(long groceriesId) {
		
		List<GroceryItem> itemList = new ArrayList<GroceryItem>();
		List<GroceryItem> allGroceriesItems = groceryItemRepository.findAll();
		
		for (GroceryItem gi : allGroceriesItems) {
			if(gi.getGroceries().getId() == groceriesId) {
				itemList.add(gi);
			}
		}
		
		return itemList;
	}
	
}
