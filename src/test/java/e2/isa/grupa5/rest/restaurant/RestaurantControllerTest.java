package e2.isa.grupa5.rest.restaurant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantDTO;
import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private RestaurantManagerRepository rmRepo;
	
	private long rId, mId;
	
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
		
		RestaurantManager rm = new RestaurantManager();
		rm.setActive(true);
		rm.setPassword_set(false);
		rm.setEmail("emailsm");
		rm.setPassword("passwordsm");
		rm.setName("name");
		rm.setSurname("surname");
		rm.setAddress("address");
		
		rm.setRestaurant(r);
		
		rm = rmRepo.save(rm);
		mId = rm.getId();
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		rmRepo.deleteAll();
		rRepo.deleteAll();
		
	}
	
	@Test
	public void createRestaurant() {
		
		RestaurantDTO rDTO = new RestaurantDTO();
		rDTO.setAddress("address1");
		rDTO.setDescription("description1");
		rDTO.setEmail("email1");
		rDTO.setName("name1");
		rDTO.setPhone("54321");

		ResponseEntity<Restaurant> response = restTemplate.postForEntity("/restaurants/create", rDTO, Restaurant.class);

		Restaurant r = response.getBody();
		
		assertNotNull(r);
		assertEquals(rDTO.getEmail(), r.getEmail());
		assertEquals(rDTO.getName(), r.getName());
	}
	
	@Test
	public void updateRestaurant() {
		
		RestaurantDTO rDTO = new RestaurantDTO();
		rDTO.setAddress("address2");
		rDTO.setDescription("description2");
		rDTO.setEmail("email2");
		rDTO.setName("name2");
		rDTO.setPhone("54321");
		rDTO.setId(rId);

		ResponseEntity<Restaurant> response = restTemplate.postForEntity("/restaurants/update", rDTO, Restaurant.class);

		Restaurant r = response.getBody();
		
		assertNotNull(r);
		assertEquals(rDTO.getEmail(), r.getEmail());
		assertEquals(rDTO.getName(), r.getName());
	}
	
	@Test
	public void getRestaurant() {
		
		ResponseEntity<Restaurant> response = restTemplate.getForEntity("/restaurants/profile/"+rId, Restaurant.class);

		Restaurant r = response.getBody();
		
		assertNotNull(r);
		assertEquals("email", r.getEmail());
		assertEquals("name", r.getName());
		
	}
	
	@Test
	public void getAll() {
		
		ResponseEntity<RestaurantDTO[]> response = restTemplate.getForEntity("/restaurants/all", RestaurantDTO[].class);

		RestaurantDTO[] rs = response.getBody();
		
		assertNotNull(rs);
		assertEquals(rs.length, 1);
		
	}
	
	@Test
	public void getForManager() {
		ResponseEntity<Restaurant> response = restTemplate.getForEntity("/restaurants/manager/"+mId, Restaurant.class);

		Restaurant r = response.getBody();
		
		assertNotNull(r);
		assertEquals("email", r.getEmail());
		assertEquals("name", r.getName());
	}
	
}
