package e2.isa.grupa5.rest.restaurant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemQtyDTO;
import e2.isa.grupa5.model.restaurant.Item;
import e2.isa.grupa5.model.restaurant.ItemType;
import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.MenuItemDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.restaurant.ItemRepository;
import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.MenuItemRepository;
import e2.isa.grupa5.repository.restaurant.MenuRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MenuControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private ItemTypeRepository itemTypeRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	private long rId, typeId, menuId;
	
	@Before
	public void setUp() throws Exception {
		ItemType i1 = new ItemType();
		i1.setDisplay_name("displayName1");
		i1.setName("name1");
		
		ItemType i2 = new ItemType();
		i2.setDisplay_name("displayName2");
		i2.setName("name2");
		
		i1 = itemTypeRepository.save(i1);
		i2 = itemTypeRepository.save(i2);
		
		
		Restaurant r = new Restaurant();
		r.setAddress("address");
		r.setDescription("description");
		r.setEmail("email");
		r.setName("name");
		r.setPhone("12345");
		
		r = rRepo.save(r);
		rId = r.getId();
		
		Menu m = new Menu();
		m.setRestaurant(r);
		
		m = menuRepository.save(m);
		
		Item it1 = new Item();
		it1.setDescription("desc1");
		it1.setImage_link("...");
		it1.setItemType(i1);
		it1.setName("name1");
		
		Item it2 = new Item();
		it2.setDescription("desc2");
		it2.setImage_link("...");
		it2.setItemType(i1);
		it2.setName("name2");
		
		it1 = itemRepository.save(it1);
		it2 = itemRepository.save(it2);
		
		MenuItem mi1 = new MenuItem();
		mi1.setItem(it1);
		mi1.setMenu(m);
		mi1.setPrice(500);
		
		MenuItem mi2 = new MenuItem();
		mi2.setItem(it2);
		mi2.setMenu(m);
		mi2.setPrice(500);
		
		mi1 = menuItemRepository.save(mi1);
		mi2 = menuItemRepository.save(mi2);
		
		typeId = i1.getId();
		menuId = m.getId();
	}
	
	@After
	public void tearDown() throws Exception {
		
		menuItemRepository.deleteAll();
		itemRepository.deleteAll();
		menuRepository.deleteAll();
		rRepo.deleteAll();
		itemTypeRepository.deleteAll();
		
		
	}
	
	@Test
	public void getMenuForRestaurant() {
		ResponseEntity<Menu> response = restTemplate.getForEntity("/restaurant/menu/"+rId, Menu.class);

		Menu itemTypes = response.getBody();
		
		assertNotNull(itemTypes);
	}
	
	@Test
	public void createMenuItem() {
		MenuItemDTO miDTO = new MenuItemDTO();
		miDTO.setImage_link("...");
		miDTO.setItemDescription("desc3");
		miDTO.setItemName("name3");
		miDTO.setItemTypeId(typeId);
		miDTO.setMenuId(menuId);
		miDTO.setPrice(500);
		
		ResponseEntity<MenuItem> response = restTemplate.postForEntity("/restaurant/menuItem/create", miDTO, MenuItem.class);

		MenuItem mi = response.getBody();
		
		assertNotNull(mi);
		assertEquals(miDTO.getPrice(), mi.getPrice(),0.0);
	}
	
	@Test
	public void getMenuItemsForRestaurant() {
		ResponseEntity<MenuItem[]> response = restTemplate.getForEntity("/restaurant/menuItem/all/"+rId, MenuItem[].class);

		MenuItem[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==2);
	}
}
