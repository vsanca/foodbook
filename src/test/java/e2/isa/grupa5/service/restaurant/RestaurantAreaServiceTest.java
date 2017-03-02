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
import e2.isa.grupa5.model.restaurant.RestaurantAreaDTO;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantAreaServiceTest {
	
	@Autowired
	private RestaurantAreaService raService;
	
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

	@Test(expected = NullPointerException.class)
	public void testCreate() {
		RestaurantAreaDTO raDTO = new RestaurantAreaDTO();
		
		RestaurantArea ra = raService.create(raDTO);
		
		assertNull(ra);
	}

	@Test
	public void testGetRestaurantAreas() {

		List<RestaurantArea> list = raService.getRestaurantAreas(new Restaurant());
		
		assertTrue(list.size()==0);
	}

}
