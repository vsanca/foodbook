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
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantManagerServiceTest {
	
	@Autowired
	private RestaurantManagerService rmService;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private RestaurantManagerRepository rmRepo;
	
	private long mId;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		Restaurant r = new Restaurant();
		r.setAddress("address");
		r.setDescription("description");
		r.setEmail("email");
		r.setName("name");
		r.setPhone("12345");
		
		r = rRepo.save(r);
		
		RestaurantManager rm = new RestaurantManager();
		rm.setActive(true);
		rm.setAddress("address");
		rm.setEmail("email");
		rm.setName("name");
		rm.setSurname("surname");
		rm.setPassword("password");
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
	public void testFindById() {
		
		RestaurantManager rm = rmService.findById(mId);
		
		assertNotNull(rm);
		assertEquals(rm.getName(), "name");
		assertEquals(rm.getAddress(), "address");
		
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

}
