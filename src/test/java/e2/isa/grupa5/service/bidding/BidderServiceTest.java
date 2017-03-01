package e2.isa.grupa5.service.bidding;

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

import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;
import e2.isa.grupa5.service.systemmanager.SystemManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidderServiceTest {

	@Autowired
	private BidderRepository bRepo;
	
	@Autowired
	private BidderService bService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	long id;
	
	@Before
	public void setUp() throws Exception {
		
		Bidder b = new Bidder();
		b.setActive(true);
		b.setAddress("address");
		b.setEmail("email");
		b.setName("name");
		b.setPassword("password");
		b.setSurname("surname");
		
		b = bRepo.save(b);
		id = b.getId();
		
	}

	@After
	public void tearDown() throws Exception {
		
		bRepo.deleteAll();
		
	}

	@Test
	public void testUpdateData() {
		
		BidderDTO bDTO = new BidderDTO();
		bDTO.setAddress("address1");
		bDTO.setName("name1");
		bDTO.setSurname("surname1");
		bDTO.setEmail("email1");
		bDTO.setId(id);
		
		Bidder b = bService.updateData(bDTO);
		
		assertEquals(bDTO.getAddress(), b.getAddress());
		assertEquals(bDTO.getName(), b.getName());
		assertEquals(bDTO.getSurname(), b.getSurname());
		assertEquals(bDTO.getEmail(), b.getEmail());
		
	}

}
