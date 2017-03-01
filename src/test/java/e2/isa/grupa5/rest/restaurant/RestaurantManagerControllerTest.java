package e2.isa.grupa5.rest.restaurant;

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

import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.model.users.BidderDTO;
import e2.isa.grupa5.model.users.RestaurantManager;
import e2.isa.grupa5.model.users.RestaurantManagerDTO;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantManagerRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantManagerControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestaurantManagerRepository smRepo;
	
	@Autowired
	private RestaurantRepository rRepo;
	
	private long id;
	
	@Before
	public void setUp() throws Exception {
		
		RestaurantManager sm = new RestaurantManager();
		sm.setActive(true);
		sm.setPassword_set(false);
		sm.setEmail("emailsm");
		sm.setPassword(passwordEncoder.encode("passwordsm"));
		sm.setName("name");
		sm.setSurname("surname");
		sm.setAddress("address");
		
		Restaurant r = new Restaurant();
		r.setAddress("address");
		r.setDescription("desc");
		r.setEmail("mail");
		r.setName("name");
		r.setPhone("1234");
		
		sm.setRestaurant(r);
		
		rRepo.save(r);
		sm = smRepo.save(sm);
		
		id = sm.getId();
	}
	
	@After
	public void tearDown() throws Exception {
		
		smRepo.delete(id);
		
	}
	
	@Test
	public void createManager() {
		
		RestaurantManagerDTO smDTO = new RestaurantManagerDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");
		
		Restaurant r = new Restaurant();
		r.setAddress("address1");
		r.setDescription("desc1");
		r.setEmail("mail1");
		r.setName("name1");
		r.setPhone("12345");
		
		r = rRepo.save(r);

		smDTO.setRestaurantId(r.getId());
		

		ResponseEntity<RestaurantManager> response = restTemplate.postForEntity("/user/rmanager/create", smDTO, RestaurantManager.class);

		RestaurantManager smResponse = response.getBody();
		
		assertEquals(smDTO.getEmail(), smResponse.getEmail());
		assertNotEquals(smDTO.getPassword(), smResponse.getPassword());
		assertTrue(passwordEncoder.matches(smDTO.getPassword(), smResponse.getPassword()));
	}
	
	@Test
	public void updateManager() {
		
		RestaurantManagerDTO smDTO = new RestaurantManagerDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");

		ResponseEntity<RestaurantManager> response = restTemplate.postForEntity("/user/rmanager/update", smDTO, RestaurantManager.class);

		RestaurantManager smResponse = response.getBody();
		
		assertNotNull(smResponse);
		assertNotEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getId() {

		ResponseEntity<RestaurantManager> response = restTemplate.getForEntity("/user/rmanager/profile/"+id, RestaurantManager.class);

		RestaurantManager smResponse = response.getBody();
			
		assertEquals("emailsm", smResponse.getEmail());
		assertNotEquals("passwordsm", smResponse.getPassword());
		assertTrue(passwordEncoder.matches("passwordsm", smResponse.getPassword()));
	}
	
	@Test
	public void changePassword() {

		RestaurantManagerDTO smDTO = new RestaurantManagerDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setNewPassword("Lozinka1");
		smDTO.setNewPasswordRepeat("Lozinka1");
		smDTO.setSurname("Prezime1");
		smDTO.setId(id);

		ResponseEntity<RestaurantManager> response = restTemplate.postForEntity("/user/rmanager/changePassword/"+id, smDTO, RestaurantManager.class);
		
		RestaurantManager b = smRepo.findByEmail("emailsm");
		
		assertFalse(passwordEncoder.matches("passwordsm", b.getPassword()));
		assertTrue(passwordEncoder.matches(smDTO.getPassword(), b.getPassword()));
	}
	
}
