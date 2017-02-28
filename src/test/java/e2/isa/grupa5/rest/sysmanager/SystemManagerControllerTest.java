package e2.isa.grupa5.rest.sysmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
	private SystemManagerRepository systemManagerRepository;
	
	@Before
	public void setUp() throws Exception {
		
		SystemManager sm = new SystemManager();
		sm.setAddress("Adresa");
		sm.setEmail("Email");
		sm.setName("Ime");
		sm.setPassword("Lozinka");
		sm.setSurname("Prezime");
		
		systemManagerRepository.save(sm);
		
	}
	
	@Test
	public void createSystemManager() {
		
		SystemManagerDTO smDTO = new SystemManagerDTO();
		smDTO.setAddress("Adresa1");
		smDTO.setEmail("Email1");
		smDTO.setName("Ime1");
		smDTO.setPassword("Lozinka1");
		smDTO.setSurname("Prezime1");


		ResponseEntity<SystemManager> response = restTemplate.withBasicAuth("Email", "Lozinka").postForEntity("/sysmanager/create", smDTO, SystemManager.class);
		
		SystemManager smResponse = response.getBody();
		
		
		assertEquals(smDTO.getEmail(), smResponse.getEmail());
		assertNotEquals(smDTO.getPassword(), smResponse.getPassword());
		assertTrue(passwordEncoder.matches(smDTO.getPassword(), smResponse.getPassword()));
	}
	
}
