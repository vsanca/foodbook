package e2.isa.grupa5.service.restaurant;

import static org.junit.Assert.*;

import java.util.List;

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
import e2.isa.grupa5.model.restaurant.RestaurantArea;
import e2.isa.grupa5.model.restaurant.RestaurantTable;
import e2.isa.grupa5.model.restaurant.RestaurantTableDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.repository.restaurant.RestaurantAreaRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantTableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantTableServiceTest {
	
	@Autowired
	private RestaurantTableService rtService;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private RestaurantManagerRepository rmRepo;
	
	@Autowired
	private RestaurantAreaRepository raRepo;
	
	@Autowired
	private RestaurantTableRepository rtRepo;
	
	private long mId, rId, raId;
	
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
		r.setPhone("phone");
		
		r = rRepo.save(r);
		rId = r.getId();
		
		RestaurantArea ra = new RestaurantArea();
		ra.setColor("color");
		ra.setName("name");
		ra.setRestaurant(r);
		
		ra = raRepo.save(ra);
		raId = ra.getId();
		
		RestaurantTable rt1 = new RestaurantTable();
		rt1.setSeats(5);
		rt1.setName("sto1");
		rt1.setFabricTable("fabric1");
		rt1.setArea(ra);
		
		rt1 = rtRepo.save(rt1);
		
		RestaurantTable rt2 = new RestaurantTable();
		rt2.setSeats(5);
		rt2.setName("sto1");
		rt2.setFabricTable("fabric1");
		rt2.setArea(ra);
		
		rt2 = rtRepo.save(rt2);
		
		RestaurantManager rm = new RestaurantManager();
		rm.setActive(true);
		rm.setAddress("address");
		rm.setEmail("email");
		rm.setName("name");
		rm.setPassword("password");
		rm.setSurname("surname");
		rm.setRestaurant(r);
		
		rm = rmRepo.save(rm);
		mId = rm.getId();
	}

	@After
	public void tearDown() throws Exception {
		
		rmRepo.deleteAll();
		rtRepo.deleteAll();
		raRepo.deleteAll();
		rRepo.deleteAll();
	}

	@Test
	public void testCreate() {
		
		RestaurantTableDTO rtDTO = new RestaurantTableDTO();
		rtDTO.setArea(raId);
		rtDTO.setFabricTable("");
		rtDTO.setName("sto");
		rtDTO.setSeats(5);
		
		RestaurantTable rt = rtService.create(rtDTO);
		
		assertNotNull(rt);
		assertEquals(rtDTO.getArea(), rt.getArea().getId());
		assertEquals(rtDTO.getName(), rt.getName());
		assertEquals(rtDTO.getSeats(), rt.getSeats());
	}

	@Test
	public void testFindAllByRestaurantId() {
		
		List<RestaurantTable> rts= rtService.findAllByRestaurantId(rId);
		
		assertNotNull(rts);
		assertTrue(rts.size()==2);
	}

	@Test
	public void testFindAllByManagerId() {
		List<RestaurantTable> rts= rtService.findAllByManagerId(mId);
		
		assertNotNull(rts);
		assertTrue(rts.size()==2);
	}

}
