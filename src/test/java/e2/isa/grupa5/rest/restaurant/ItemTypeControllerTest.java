package e2.isa.grupa5.rest.restaurant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.restaurant.ItemType;
import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.SystemManagerDTO;
import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemTypeControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	ItemTypeRepository itemTypeRepository;
	
	@Before
	public void setUp() throws Exception {
		ItemType it = new ItemType();
		
		it.setDisplay_name("Item 1");
		it.setName("item1");
		
		itemTypeRepository.save(it);
		
		ItemType it1 = new ItemType();
		
		it1.setDisplay_name("Item 2");
		it1.setName("item2");
		
		itemTypeRepository.save(it1);
	}
	
	@After
	public void tearDown() throws Exception {
		
		itemTypeRepository.deleteAll();
		
	}
	
	@Test
	public void getAll() {

		ResponseEntity<ItemType[]> response = restTemplate.getForEntity("/menu/itemTypes/all", ItemType[].class);

		ItemType[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==2);
	}
	
}
