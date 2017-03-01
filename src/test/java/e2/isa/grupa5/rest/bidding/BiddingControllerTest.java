package e2.isa.grupa5.rest.bidding;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.bidding.Bidding;
import e2.isa.grupa5.model.bidding.BiddingConstants;
import e2.isa.grupa5.model.bidding.BiddingItem;
import e2.isa.grupa5.model.groceries.Groceries;
import e2.isa.grupa5.model.groceries.GroceriesConstants;
import e2.isa.grupa5.model.groceries.GroceryItem;
import e2.isa.grupa5.model.groceries.GroceryItemQty;
import e2.isa.grupa5.model.groceries.GroceryItemType;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.model.users.Bidder;
import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.bidding.BiddingItemRepository;
import e2.isa.grupa5.repository.bidding.BiddingRepository;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;
import e2.isa.grupa5.service.bidding.BiddingItemService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BiddingControllerTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	BiddingItemService biddingItemService;
	
	@Autowired
	BiddingRepository biddingRepository;
	
	@Autowired
	BiddingItemRepository biddingItemRepository;
	
	@Autowired
	BidderRepository bidderRepository;
	
	@Autowired
	GroceriesRepository groceriesRepository;
	
	@Autowired
	GroceryItemRepository giRepo;
	
	@Autowired 
	GroceryItemQuantityRepository giqRepo;
	
	@Autowired
	GroceryItemTypeRepository gitRepo;
	
	@Autowired
	RestaurantRepository rRepo;
	
	private long bidderId, groceriesId, biddingId;
	
	@Before
	public void setUp() throws Exception {
		
		Bidder b = new Bidder();
		b.setActive(true);
		b.setAddress("address");
		b.setEmail("email");
		b.setName("name");
		b.setSurname("surname");
		b.setPassword("password");
		b.setPassword_set(true);
		
		b = bidderRepository.save(b);
		bidderId = b.getId();
		
		
		Restaurant r = new Restaurant();
		r.setAddress("address");
		r.setDescription("desc");
		r.setEmail("mail");
		r.setName("name");
		r.setPhone("1234");
		
		r = rRepo.save(r);
		
		
		Groceries g = new Groceries();
		g.setFrom_date(Calendar.getInstance().getTime());
		g.setTo_date(Calendar.getInstance().getTime());
		g.setRestaurant(rRepo.findById(r.getId()));
		g.setStatus("OPEN");
		
		g = groceriesRepository.save(g);
		groceriesId = g.getId();
		
		GroceryItemQty giq = new GroceryItemQty();
		giq.setName("item1");
		
		giq = giqRepo.save(giq);
		
		GroceryItemType git = new GroceryItemType();
		git.setName("item1");
		
		git = gitRepo.save(git);
		
		
		GroceryItem gi = new GroceryItem();
		gi.setGroceries(g);
		gi.setGroceryItemQty(giq);
		gi.setGroceryItemType(git);
		gi.setName("g1");
		gi.setQuantity(50);
		
		gi = giRepo.save(gi);
		
		GroceryItem gi1 = new GroceryItem();
		gi1.setGroceries(g);
		gi1.setGroceryItemQty(giq);
		gi1.setGroceryItemType(git);
		gi1.setName("g2");
		gi1.setQuantity(80);
		
		gi1 = giRepo.save(gi1);
		
		
		Bidding bd = new Bidding();
		bd.setBidder(b);
		bd.setCurrency("RSD");
		bd.setPrice(800);
		bd.setStatus(BiddingConstants.ACTIVE);
		bd.setTimestamp(new Date());
		bd.setGroceries(g);
		
		bd = biddingRepository.save(bd);
		biddingId = bd.getId();
		
		BiddingItem bi = new BiddingItem();
		bi.setBidding(bd);
		bi.setGroceryItem(gi);
		bi.setGroceryItemQty(gi.getGroceryItemQty());
		bi.setName("bi1");
		bi.setPrice(500);
		bi.setQuantity(50);
		
		bi = biddingItemRepository.save(bi);
		
		BiddingItem bi1 = new BiddingItem();
		bi1.setBidding(bd);
		bi1.setGroceryItem(gi);
		bi1.setGroceryItemQty(gi.getGroceryItemQty());
		bi1.setName("bi2");
		bi1.setPrice(500);
		bi1.setQuantity(50);
		
		bi1 = biddingItemRepository.save(bi1);
		
	}
	
	@After
	public void tearDown() throws Exception {
		biddingItemRepository.deleteAll();
		biddingRepository.deleteAll();
		giRepo.deleteAll();
		gitRepo.deleteAll();
		giqRepo.deleteAll();
		rRepo.deleteAll();
		bidderRepository.deleteAll();
	}
	
	@Test
	public void getByBidder() {
		ResponseEntity<Bidding[]> response = restTemplate.getForEntity("/bidding/getByBidder/"+bidderId, Bidding[].class);

		Bidding[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==1);
		assertTrue(itemTypes[0].getBidder().getId() == bidderId);
	}
	
	@Test
	public void getActiveByBidder() {
		ResponseEntity<Bidding[]> response = restTemplate.getForEntity("/bidding/getActiveByBidder/"+bidderId, Bidding[].class);

		Bidding[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==1);
		assertTrue(itemTypes[0].getBidder().getId() == bidderId);
	}
	
	@Test
	public void getBidItemsByBidding() {
		ResponseEntity<BiddingItem[]> response = restTemplate.getForEntity("/bidding/getByBidder/"+biddingId, BiddingItem[].class);

		BiddingItem[] itemTypes = response.getBody();
		
		assertTrue(itemTypes.length==2);
	}
	
	@Test
	public void getActiveNumber() {
		ResponseEntity<Integer> response = restTemplate.getForEntity("/bidding/getNumberOfActiveBiddingsForBidder/"+bidderId, Integer.class);

		Integer itemTypes = response.getBody();
		
		assertTrue(itemTypes==1);
	}
	
}
