package e2.isa.grupa5.rest.bidding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.SystemManagerDTO;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BidderControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BidderRepository smRepo;
	
	private long id;
	
	@Before
	public void setUp() throws Exception {
		
		Bidder sm = new Bidder();
		sm.setActive(true);
		sm.setPassword_set(false);
		sm.setEmail("emailsm");
		sm.setPassword(passwordEncoder.encode("passwordsm"));
		sm.setName("name");
		sm.setSurname("surname");
		sm.setAddress("address");

		
		smRepo.save(sm);
		
		id = sm.getId();
	}
	
	@After
	public void tearDown() throws Exception {
		
		smRepo.delete(id);
		
	}
	
	@Test
	public void createBidder() {
		
		BidderDTO smDTO = new BidderDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");

		ResponseEntity<Bidder> response = restTemplate.postForEntity("/user/bidder/create", smDTO, Bidder.class);

		Bidder smResponse = response.getBody();
		
		assertEquals(smDTO.getEmail(), smResponse.getEmail());
		assertNotEquals(smDTO.getPassword(), smResponse.getPassword());
		assertTrue(passwordEncoder.matches(smDTO.getPassword(), smResponse.getPassword()));
	}
	
	@Test
	public void updateBidder() {
		
		BidderDTO smDTO = new BidderDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");

		ResponseEntity<Bidder> response = restTemplate.postForEntity("/user/bidder/update", smDTO, Bidder.class);

		Bidder smResponse = response.getBody();
		
		assertNotNull(smResponse);
		assertNotEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getId() {

		ResponseEntity<Bidder> response = restTemplate.getForEntity("/user/bidder/profile/"+id, Bidder.class);

		Bidder smResponse = response.getBody();
			
		assertEquals("emailsm", smResponse.getEmail());
		assertNotEquals("passwordsm", smResponse.getPassword());
		assertTrue(passwordEncoder.matches("passwordsm", smResponse.getPassword()));
	}
	
	@Test
	public void changePassword() {

		BidderDTO smDTO = new BidderDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setOldPassword("passwordsm");
		smDTO.setNewPassword("Lozinka1");
		smDTO.setSurname("Prezime1");
		smDTO.setId(id);

		ResponseEntity<Bidder> response = restTemplate.postForEntity("/user/bidder/changePassword/", smDTO, Bidder.class);
		
		Bidder b = smRepo.findByEmail("emailsm");
		
		assertFalse(passwordEncoder.matches("passwordsm", b.getPassword()));
		assertTrue(passwordEncoder.matches(smDTO.getPassword(), b.getPassword()));
	}
	
}
