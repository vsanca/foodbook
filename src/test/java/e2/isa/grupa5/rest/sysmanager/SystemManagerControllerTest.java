package e2.isa.grupa5.rest.sysmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.SystemManagerDTO;
import e2.isa.grupa5.repository.sysmanager.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemManagerControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SystemManagerRepository smRepo;
	
	private long id;
	
	@Before
	public void setUp() throws Exception {
		
		SystemManager sm = new SystemManager();
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
	public void createSystemManager() {
		
		SystemManagerDTO smDTO = new SystemManagerDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");

		ResponseEntity<SystemManager> response = restTemplate.postForEntity("/sysmanager/create", smDTO, SystemManager.class);

		SystemManager smResponse = response.getBody();
		
		assertEquals(smDTO.getEmail(), smResponse.getEmail());
		assertNotEquals(smDTO.getPassword(), smResponse.getPassword());
		assertTrue(passwordEncoder.matches(smDTO.getPassword(), smResponse.getPassword()));
	}
	
	@Test
	public void updateSystemManager() {
		
		SystemManagerDTO smDTO = new SystemManagerDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");

		ResponseEntity<SystemManager> response = restTemplate.postForEntity("/sysmanager/update", smDTO, SystemManager.class);

		SystemManager smResponse = response.getBody();
		
		assertNotNull(smResponse);
		assertNotEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getAll() {
		
		ResponseEntity<SystemManager[]> response = restTemplate.getForEntity("/sysmanager/getAll", SystemManager[].class);

		SystemManager[] smResponse = response.getBody();
		
		System.out.println(smResponse[0].getEmail());
		
		assertTrue(smResponse.length>0);
		
		assertEquals("emailsm", smResponse[0].getEmail());
		assertNotEquals("passwordsm", smResponse[0].getPassword());
		assertTrue(passwordEncoder.matches("passwordsm", smResponse[0].getPassword()));
	}
	
	@Test
	public void getId() {

		ResponseEntity<SystemManager> response = restTemplate.getForEntity("/sysmanager/get/"+id, SystemManager.class);

		SystemManager smResponse = response.getBody();
			
		assertEquals("emailsm", smResponse.getEmail());
		assertNotEquals("passwordsm", smResponse.getPassword());
		assertTrue(passwordEncoder.matches("passwordsm", smResponse.getPassword()));
	}
	
}
