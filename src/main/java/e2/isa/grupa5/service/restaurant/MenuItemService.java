package e2.isa.grupa5.service.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e2.isa.grupa5.model.restaurant.Item;
import e2.isa.grupa5.model.restaurant.ItemType;
import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.MenuItemDTO;
import e2.isa.grupa5.repository.restaurant.ItemRepository;
import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;

/**
 * Funkcija 2.5:
 * - kreiranje stavki menija restorana
 * 
 * @author Viktor
 *
 */
@Service
public class MenuItemService {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MenuItemRepository menuItemRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemTypeRepository itemTypeRepository;
	
	
	public MenuItem create(MenuItemDTO menuItemDTO) {
		
		MenuItem menuItem = new MenuItem();
		
		ItemType type = itemTypeRepository.findById(menuItemDTO.getItemTypeId());
		Menu m = menuRepository.findById(menuItemDTO.getMenuId());
		
		Item i = new Item();
		i.setName(menuItemDTO.getItemName());
		i.setDescription(menuItemDTO.getItemDescription());
		i.setItemType(type);
		
		menuItem.setItem(i);
		menuItem.setMenu(m);
		
		try {
			itemRepository.save(i);
			menuItem = menuItemRepository.save(menuItem);
			return menuItem;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
