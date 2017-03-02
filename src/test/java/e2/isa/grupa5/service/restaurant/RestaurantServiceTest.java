package e2.isa.grupa5.service.restaurant;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.restaurant.RestaurantDTO;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceTest {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	private long rId;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		RestaurantDTO rDTO = new RestaurantDTO();
		rDTO.setAddress("address");
		rDTO.setDescription("description");
		rDTO.setEmail("email");
		rDTO.setName("name");
		rDTO.setPhone("12345");
		
		Restaurant r = restaurantService.addRestaurant(rDTO);
		rId = r.getId();
		
	}

	@After
	public void tearDown() throws Exception {
		
		rRepo.deleteAll();
		
	}

	@Test
	public void testAddRestaurant() {
		RestaurantDTO rDTO = new RestaurantDTO();
		rDTO.setAddress("address1");
		rDTO.setDescription("description1");
		rDTO.setEmail("email1");
		rDTO.setName("name1");
		rDTO.setPhone("12345");
		
		Restaurant r = restaurantService.addRestaurant(rDTO);
		
		assertNotNull(r);
		assertEquals(rDTO.getAddress(), r.getAddress());
	}

	@Test
	public void testEditRestaurant() {
		RestaurantDTO rDTO = new RestaurantDTO();
		rDTO.setAddress("address1");
		rDTO.setDescription("description1");
		rDTO.setEmail("email1");
		rDTO.setName("name1");
		rDTO.setPhone("12345");
		rDTO.setId(rId);
		
		Restaurant r = restaurantService.editRestaurant(rDTO);
		
		assertNotNull(r);
		assertEquals(rDTO.getAddress(), r.getAddress());
	}

}
