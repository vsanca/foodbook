package e2.isa.grupa5.service.bartender;

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

import e2.isa.grupa5.model.users.Bartender;
import e2.isa.grupa5.model.users.BartenderDTO;
import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;

import e2.isa.grupa5.model.users.Waiter;
import e2.isa.grupa5.model.users.WaiterDTO;
import e2.isa.grupa5.repository.bartender.BartenderRepository;
import e2.isa.grupa5.repository.bidding.BidderRepository;

import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;
import e2.isa.grupa5.repository.waiter.WaiterRepository;
import e2.isa.grupa5.service.systemmanager.SystemManagerService;
import e2.isa.grupa5.service.waiter.WaiterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BartenderServiceTest {

	@Autowired
	BartenderRepository wtRepo;
	
	@Autowired
	BartenderService wtService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	long id;
	
	@Before
	public void setUp() throws Exception {
		
		Bartender wt = new Bartender();
		wt.setActive(true);
		wt.setAddress("address");
		wt.setEmail("email");
		wt.setName("name");
		wt.setPassword("password");
		wt.setSurname("surname");
		
		wt = wtRepo.save(wt);
		id = wt.getId();
		
	}

	@After
	public void tearDown() throws Exception {
		
		wtRepo.deleteAll();
		
	}

	@Test
	public void testUpdateData() {
		
		BartenderDTO wtDTO = new BartenderDTO();
		wtDTO.setAddress("address1");
		wtDTO.setName("name1");
		wtDTO.setSurname("surname1");
		wtDTO.setEmail("email1");
		wtDTO.setId(id);
		
		Bartender wt = wtService.updateData(wtDTO);
		
		assertEquals(wtDTO.getAddress(), wt.getAddress());
		assertEquals(wtDTO.getName(), wt.getName());
		assertEquals(wtDTO.getSurname(), wt.getSurname());
		assertEquals(wtDTO.getEmail(), wt.getEmail());
		
	}

}
