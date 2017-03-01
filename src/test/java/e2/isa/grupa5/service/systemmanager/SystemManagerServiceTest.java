package e2.isa.grupa5.service.systemmanager;

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

import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.SystemManagerDTO;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerServiceTest {
	
	@Autowired
	private SystemManagerRepository smRepo;
	
	@Autowired
	private SystemManagerService smService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	long id;
	
	@Before
	public void setUp() throws Exception {
		SystemManager sm = new SystemManager();
		
		sm.setActive(true);
		sm.setAddress("address");
		sm.setEmail("email");
		sm.setName("name");
		sm.setSurname("surname");
		sm.setPassword("password");
		
		sm = smRepo.save(sm);
		id = sm.getId();
	}

	@After
	public void tearDown() throws Exception {
		
		smRepo.deleteAll();
		
	}

	@Test
	public void testUpdateData() {
		
		SystemManagerDTO smDTO = new SystemManagerDTO();
		
		smDTO.setAddress("address1");
		smDTO.setName("name1");
		smDTO.setSurname("surname1");
		smDTO.setId(id);
		
		SystemManager s = smService.updateData(smDTO);
		
		
		assertEquals(smDTO.getAddress(), s.getAddress());
		assertEquals(smDTO.getName(), s.getName());
		assertEquals(smDTO.getSurname(), s.getSurname());
	}

}
