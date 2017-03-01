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
import e2.isa.grupa5.model.groceries.GroceryItemType;
import e2.isa.grupa5.model.groceries.GroceryItemTypeDTO;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroceryItemTypeControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	GroceryItemTypeRepository giqRepository;
	
	@Before
	public void setUp() throws Exception {
		GroceryItemType it = new GroceryItemType();
		it.setName("item1");
		
		giqRepository.save(it);
		
		GroceryItemType it1 = new GroceryItemType();
		
		it1.setName("item2");
		
		giqRepository.save(it1);
	}
	
	@After
	public void tearDown() throws Exception {
		
		giqRepository.deleteAll();
		
	}
	
	@Test
	public void getAll() {
		ResponseEntity<GroceryItemType[]> response = restTemplate.getForEntity("/groceries/type/all", GroceryItemType[].class);

		GroceryItemType[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==2);
		
	}
	
	@Test
	public void createNew() {
		
		GroceryItemTypeDTO giqDTO = new GroceryItemTypeDTO();
		giqDTO.setName("123");
		
		ResponseEntity<GroceryItemType> response = restTemplate.postForEntity("/groceries/type/new", giqDTO, GroceryItemType.class);

		GroceryItemType giq = response.getBody();
		
		assertEquals(giqDTO.getName(), giq.getName());
	}
	
}
