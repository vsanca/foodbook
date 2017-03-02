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

import e2.isa.grupa5.model.restaurant.MenuItem;
import e2.isa.grupa5.model.restaurant.MenuItemDTO;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuItemServiceTest {
	
	@Autowired
	private MenuItemService miService;
	
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
		
		MenuItemDTO miDTO = new MenuItemDTO();
		
		MenuItem mi = miService.create(miDTO);
		assertNull(mi);
	}

}
