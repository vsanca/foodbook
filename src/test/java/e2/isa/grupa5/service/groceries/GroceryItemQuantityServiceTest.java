package e2.isa.grupa5.service.groceries;

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

import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemQtyDTO;
import e2.isa.grupa5.service.systemmanager.SystemManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroceryItemQuantityServiceTest {
	
	@Autowired
	private GroceryItemQuantityService giqService;
	
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
		GroceryItemQtyDTO giqDTO = new GroceryItemQtyDTO();
		
		giqDTO.setName("name");
		
		GroceryItemQty giq = giqService.create(giqDTO);
		
		assertNotNull(giq);
		assertEquals(giqDTO.getName(), giq.getName());
	}

}
