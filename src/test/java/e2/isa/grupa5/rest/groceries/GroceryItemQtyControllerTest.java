package e2.isa.grupa5.rest.groceries;

import static org.junit.Assert.assertEquals;
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

import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemQtyDTO;
import e2.isa.grupa5.model.restaurant.ItemType;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.restaurant.ItemTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroceryItemQtyControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	GroceryItemQuantityRepository giqRepository;
	
	@Before
	public void setUp() throws Exception {
		GroceryItemQty it = new GroceryItemQty();
		it.setName("item1");
		
		giqRepository.save(it);
		
		GroceryItemQty it1 = new GroceryItemQty();

		it1.setName("item2");
		
		giqRepository.save(it1);
	}
	
	@After
	public void tearDown() throws Exception {
		
		giqRepository.deleteAll();
		
	}
	
	@Test
	public void getAll() {
		ResponseEntity<GroceryItemQty[]> response = restTemplate.getForEntity("/groceries/quantity/all", GroceryItemQty[].class);

		GroceryItemQty[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==2);
		
	}
	
	@Test
	public void createNew() {
		
		GroceryItemQtyDTO giqDTO = new GroceryItemQtyDTO();
		giqDTO.setName("123");
		
		ResponseEntity<GroceryItemQty> response = restTemplate.postForEntity("/groceries/quantity/new", giqDTO, GroceryItemQty.class);

		GroceryItemQty giq = response.getBody();
		
		assertEquals(giqDTO.getName(), giq.getName());
	}
}
