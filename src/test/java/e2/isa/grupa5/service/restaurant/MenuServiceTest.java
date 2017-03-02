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

import e2.isa.grupa5.model.restaurant.Menu;
import e2.isa.grupa5.model.restaurant.MenuDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.groceries.GroceryItemQuantityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {
	
	@Autowired
	private RestaurantRepository rRepo;
	
	@Autowired
	private MenuService mService;
	
	private long rId;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		
		MenuDTO mDTO = new MenuDTO();
		mDTO.setRestaurantId(rId);
		
		Menu m = mService.create(mDTO);
		
		assertNull(m);
		
	}

}
