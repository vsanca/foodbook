package e2.isa.grupa5.rest.groceries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceriesConstants;
import e2.isa.grupa5.model.groceries.GroceriesDTO;
import e2.isa.grupa5.model.groceries.GroceryItem;
import e2.isa.grupa5.model.groceries.GroceryItemDTO;
import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemType;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantAreaDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroceriesControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private GroceriesRepository gRepo;
	
	@Autowired
	private GroceryItemRepository giRepo;
	
	@Autowired
	private GroceryItemTypeRepository gitRepo;
	
	@Autowired 
	GroceryItemQuantityRepository giqRepo;
	
	
	private long rId, gId, gqId, gtId;
	
	
	@Before
	public void setUp() throws Exception {
		
		Restaurant r = new Restaurant();
		r.setAddress("address");
		r.setDescription("description");
		r.setEmail("email");
		r.setName("name");
		r.setPhone("12345");
		
		r = rRepo.save(r);
		rId = r.getId();
		
		Groceries g = new Groceries();
		g.setFrom_date(Calendar.getInstance().getTime());
		g.setTo_date(Calendar.getInstance().getTime());
		g.setRestaurant(rRepo.findById(r.getId()));
		g.setStatus("OPEN");
		
		g = gRepo.save(g);
		gId = g.getId();
		
		GroceryItemQty giq = new GroceryItemQty();
		giq.setName("item1");
		
		giq = giqRepo.save(giq);
		gqId = giq.getId();
		
		GroceryItemType git = new GroceryItemType();
		git.setName("item1");
		
		git = gitRepo.save(git);
		gtId = git.getId();
		
		GroceryItem gi = new GroceryItem();
		gi.setGroceries(g);
		gi.setGroceryItemQty(giq);
		gi.setGroceryItemType(git);
		gi.setName("g1");
		gi.setQuantity(50);
		
		gi = giRepo.save(gi);
		
		GroceryItem gi1 = new GroceryItem();
		gi1.setGroceries(g);
		gi1.setGroceryItemQty(giq);
		gi1.setGroceryItemType(git);
		gi1.setName("g2");
		gi1.setQuantity(80);
		
		gi1 = giRepo.save(gi1);
	}
	
	@After
	public void tearDown() throws Exception {
		
		giRepo.deleteAll();
		gitRepo.deleteAll();
		gRepo.deleteAll();
		rRepo.deleteAll();
		
	}
	
	@Test
	public void createNewGroceries() {
		
		GroceriesDTO gDTO = new GroceriesDTO();
		gDTO.setFrom_date(new Date());
		gDTO.setTo_date(new Date());
		gDTO.setRestaurantId(rId);
		gDTO.setStatus(GroceriesConstants.OPEN);
		
		ResponseEntity<Groceries> response = restTemplate.postForEntity("/groceries/new", gDTO, Groceries.class);

		Groceries g = response.getBody();
		
		assertNotNull(g);
		assertEquals(gDTO.getStatus(), g.getStatus());
	}
	
	@Test
	public void createNewGroceryItem() {
		
		GroceryItemDTO giDTO = new GroceryItemDTO();
		giDTO.setGroceriesId(gId);
		giDTO.setQuantity(50);
		giDTO.setName("name");
		giDTO.setGroceriesItemQty(gqId);
		giDTO.setGroceriesItemType(gtId);
		
		ResponseEntity<GroceryItem> response = restTemplate.postForEntity("/groceries/item/new", giDTO, GroceryItem.class);

		GroceryItem gi = response.getBody();
		
		assertNotNull(gi);
		assertEquals(giDTO.getName(), gi.getName());
		assertEquals(giDTO.getQuantity(), gi.getQuantity());
	}
	
	@Test
	public void getAll() {
		
		ResponseEntity<Groceries[]> response = restTemplate.getForEntity("/groceries/getAll", Groceries[].class);

		Groceries[] gs = response.getBody();
		
		assertNotNull(gs);
		assertEquals(gs.length, 1);
	}
	
	@Test
	public void getAllForRestaurant() {
		
		ResponseEntity<Groceries[]> response = restTemplate.getForEntity("/groceries/getAllForRestaurant/"+rId, Groceries[].class);

		Groceries[] gs = response.getBody();
		
		assertNotNull(gs);
		assertEquals(gs.length, 1);
	}
	
	@Test
	public void getAllItems() {
		
		ResponseEntity<GroceryItem[]> response = restTemplate.getForEntity("/groceries/getItems/"+gId, GroceryItem[].class);

		GroceryItem[] gis = response.getBody();
		
		assertNotNull(gis);
		assertEquals(gis.length, 2);
	}
	
	@Test
	public void open() {
		
		ResponseEntity<Groceries> response = restTemplate.postForEntity("/groceries/open", gId, Groceries.class);

		Groceries g = response.getBody();
		
		assertNotNull(g);
		assertEquals(g.getStatus(), GroceriesConstants.OPEN);
	}
	
	@Test
	public void close() {
		
		ResponseEntity<Groceries> response = restTemplate.postForEntity("/groceries/close", gId, Groceries.class);

		Groceries g = response.getBody();
		
		assertNotNull(g);
		assertEquals(g.getStatus(), GroceriesConstants.CLOSED);
	}
	
	@Test
	public void expire() {
		
		ResponseEntity<Groceries> response = restTemplate.postForEntity("/groceries/expired", gId, Groceries.class);

		Groceries g = response.getBody();
		
		assertNotNull(g);
		assertEquals(g.getStatus(), GroceriesConstants.EXPIRED);
	}
}
