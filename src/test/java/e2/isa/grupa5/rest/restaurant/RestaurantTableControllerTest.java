package e2.isa.grupa5.rest.restaurant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.restaurant.RestaurantTableDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantTableControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private RestaurantAreaRepository raRepo;
	
	@Autowired
	private RestaurantTableRepository rtRepo;
	
	private long rId, aId;
	
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
		
		RestaurantArea ra = new RestaurantArea();
		ra.setColor("white");
		ra.setName("area1");
		ra.setRestaurant(r);
		
		ra = raRepo.save(ra);
		aId = ra.getId();
		
		RestaurantTable rt1 = new RestaurantTable();
		rt1.setArea(ra);
		rt1.setFabricTable("");
		rt1.setName("table1");
		rt1.setSeats(5);
		
		RestaurantTable rt2 = new RestaurantTable();
		rt2.setArea(ra);
		rt2.setFabricTable("");
		rt2.setName("table2");
		rt2.setSeats(5);
		
		rt1 = rtRepo.save(rt1);
		rt2 = rtRepo.save(rt2);
	}
	
	@After
	public void tearDown() throws Exception {
		
		rtRepo.deleteAll();
		raRepo.deleteAll();
		rRepo.deleteAll();
		
	}
	
	@Test
	public void createTable() {
		
		RestaurantTableDTO rtDTO = new RestaurantTableDTO();
		rtDTO.setArea(aId);
		rtDTO.setFabricTable("");
		rtDTO.setName("table3");
		rtDTO.setSeats(5);
		
		ResponseEntity<RestaurantTable> response = restTemplate.postForEntity("/restaurants/tables/create", rtDTO, RestaurantTable.class);

		RestaurantTable rt = response.getBody();
		
		assertNotNull(rt);
		assertEquals(rtDTO.getSeats(), rt.getSeats());
		assertEquals(rtDTO.getName(), rt.getName());
	}
	
	@Test
	public void getForRestaurant() {
		ResponseEntity<RestaurantTable[]> response = restTemplate.getForEntity("/restaurants/tables/getForRestaurant/"+rId, RestaurantTable[].class);

		RestaurantTable[] rts = response.getBody();
		
		assertNotNull(rts);
		assertEquals(rts.length, 2);
	}
	
}
