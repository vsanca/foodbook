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

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantAreaDTO;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantAreaControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private RestaurantAreaRepository raRepo;
	
	@Autowired
	private RestaurantManagerRepository rmRepo;
	
	private long mId;
	
	
	@Before
	public void setUp() throws Exception {
		
		Restaurant r = new Restaurant();
		r.setAddress("address");
		r.setDescription("description");
		r.setEmail("email");
		r.setName("name");
		r.setPhone("12345");
		
		r = rRepo.save(r);
		
		RestaurantArea ra = new RestaurantArea();
		ra.setColor("white");
		ra.setName("area1");
		ra.setRestaurant(r);
		
		ra = raRepo.save(ra);
		
		RestaurantArea ra1 = new RestaurantArea();
		ra1.setColor("blue");
		ra1.setName("area2");
		ra1.setRestaurant(r);
		
		ra1 = raRepo.save(ra1);
		
		RestaurantManager rm = new RestaurantManager();
		rm.setActive(true);
		rm.setAddress("address");
		rm.setEmail("email");
		rm.setName("name");
		rm.setPassword("pass");
		rm.setSurname("surname");
		rm.setRestaurant(r);
		
		rm = rmRepo.save(rm);
		mId = rm.getId();
	}
	
	@After
	public void tearDown() throws Exception {
		
		rmRepo.deleteAll();
		raRepo.deleteAll();
		rRepo.deleteAll();
		
	}
	
	@Test
	public void createNewArea() {
		
		RestaurantAreaDTO raDTO = new RestaurantAreaDTO();
		raDTO.setColor("yellow");
		raDTO.setName("area3");
		raDTO.setRestaurantManagerId(mId);
		
		ResponseEntity<RestaurantArea> response = restTemplate.postForEntity("/restaurants/areas/new", raDTO, RestaurantArea.class);

		RestaurantArea ra = response.getBody();
		
		assertNotNull(ra);
		assertEquals(raDTO.getColor(), ra.getColor());
		assertEquals(raDTO.getName(), ra.getName());
	}
	
	@Test
	public void getAreasForManager() {
		
		ResponseEntity<RestaurantArea[]> response = restTemplate.getForEntity("/restaurants/areas/manager/"+mId, RestaurantArea[].class);

		RestaurantArea[] ras = response.getBody();
		
		assertNotNull(ras);
		assertEquals(ras.length, 2);
	}
	
}
