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

import e2.isa.grupa5.repository.bidding.BidderRepository;
import e2.isa.grupa5.repository.bidding.BiddingItemRepository;
import e2.isa.grupa5.repository.bidding.BiddingRepository;
import e2.isa.grupa5.repository.groceries.GroceriesRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemQuantityRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemRepository;
import e2.isa.grupa5.repository.groceries.GroceryItemTypeRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiddingServiceTest {
	
	@Autowired
	BiddingService biddingService;
	
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
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
	}

	@Test
	public void testUpdate() {
	}

}
