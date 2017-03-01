package e2.isa.grupa5.service.groceries;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceriesDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroceriesServiceTest {
	
	@Autowired
	private GroceriesService gService;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	private long rId;
	
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
		
	}

	@After
	public void tearDown() throws Exception {
		
		rRepo.deleteAll();
		
	}

	@Test
	public void testCreate() {
		
		GroceriesDTO gDTO = new GroceriesDTO();
		gDTO.setFrom_date(new Date());
		gDTO.setTo_date(new Date());
		gDTO.setRestaurantId(rId);
		gDTO.setStatus("status");
		
		Groceries g = new Groceries();
		
		assertNotNull(g);	
	}

}
