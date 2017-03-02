package e2.isa.grupa5.service.chef;

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
import e2.isa.grupa5.model.users.Chef;
import e2.isa.grupa5.model.users.ChefDTO;
import e2.isa.grupa5.repository.chef.ChefRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChefServiceTest {

	@Autowired
	ChefRepository chRepo;
	
	@Autowired
	ChefService chService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	long id;
	
	@Before
	public void setUp() throws Exception {
		
		Chef ch = new Chef();
		ch.setActive(true);
		ch.setAddress("address");
		ch.setEmail("email");
		ch.setName("name");
		ch.setPassword("password");
		ch.setSurname("surname");
		
		ch = chRepo.save(ch);
		id = ch.getId();
		
	}

	@After
	public void tearDown() throws Exception {
		
		chRepo.deleteAll();
		
	}

	@Test
	public void testUpdateData() {
		
		ChefDTO chDTO = new ChefDTO();
		chDTO.setAddress("address1");
		chDTO.setName("name1");
		chDTO.setSurname("surname1");
		chDTO.setEmail("email1");
		chDTO.setId(id);
		
		Chef ch = chService.updateData(chDTO);
		
		assertEquals(chDTO.getAddress(), ch.getAddress());
		assertEquals(chDTO.getName(), ch.getName());
		assertEquals(chDTO.getSurname(), ch.getSurname());
		assertEquals(chDTO.getEmail(), ch.getEmail());
		
	}

}
