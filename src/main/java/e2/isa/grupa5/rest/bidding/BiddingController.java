package e2.isa.grupa5.rest.bidding;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.bidding.Bidding;
import e2.isa.grupa5.model.bidding.BiddingConstants;
import e2.isa.grupa5.model.bidding.BiddingDTO;
import e2.isa.grupa5.model.bidding.BiddingItem;
import e2.isa.grupa5.model.bidding.BiddingItemDTO;
import e2.isa.grupa5.repository.bidding.BiddingItemRepository;
import e2.isa.grupa5.repository.bidding.BiddingRepository;
import e2.isa.grupa5.service.bidding.BiddingItemService;
import e2.isa.grupa5.service.bidding.BiddingService;

/**
 * 
 * REST services for {@link Bidding}.
 * 
 * Funkcionalnost 2.3:
 * - upravljanje ponudama
 * 
 * Funkcionalnost 2.5:
 * - upravljanje ponudama
 * 
 * @author Viktor
 *
 */
@RestController
@RequestMapping("/bidding")
public class BiddingController {
	
	@Autowired
	BiddingService biddingService;
	
	@Autowired
	BiddingItemService biddingItemService;
	
	@Autowired
	BiddingRepository biddingRepository;
	
	@Autowired
	BiddingItemRepository biddingItemRepository;
	
	
	// + Ograničenje da se ne može kreirati kad je ponuda neaktivna (dok traje konkurs)!
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity newBidding(@RequestBody BiddingDTO bDTO) {
		
		Bidding b = biddingService.create(bDTO);
		
		return new ResponseEntity<>(b, HttpStatus.CREATED);
	}
	
	// + Ograničenje da se ne može kreirati kad je ponuda neaktivna (dok traje konkurs)!
	@RequestMapping(value = "/biddingItem/new", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity newBiddingItem(@RequestBody BiddingItemDTO biDTO) {
		
		BiddingItem bi = biddingItemService.create(biDTO);
		
		return new ResponseEntity<>(bi, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity acceptBidding(@RequestBody long bId) {
	
		Bidding b = biddingRepository.findById(bId);
		
		if(b != null) {
			b.setStatus(BiddingConstants.ACCEPTED);
			b = biddingRepository.save(b);
			return new ResponseEntity<>(b, HttpStatus.OK);
					
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity rejectBidding(@RequestBody long bId) {
	
		Bidding b = biddingRepository.findById(bId);
		
		if(b != null) {
			b.setStatus(BiddingConstants.REJECTED);
			b = biddingRepository.save(b);
			return new ResponseEntity<>(b, HttpStatus.OK);
					
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}
	
	// + Ograničenje da se ne može ažurirati kad je ponuda završena (dok traje konkurs)!
	@RequestMapping(value = "/update/{bId}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity updateBidding(@PathVariable long bId, @RequestBody BiddingDTO bDTO) {
		
		Bidding b = biddingService.update(bDTO, bId);
		
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	// + Ograničenje da se ne može ažurirati kad je ponuda završena (dok traje konkurs)!
	@RequestMapping(value = "/biddingItem/update/{biId}", method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity updateBiddingItem(@PathVariable long biId, @RequestBody BiddingItemDTO biDTO) {
		
		BiddingItem bi = biddingItemService.update(biDTO, biId);
		
		return new ResponseEntity<>(bi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getByBidder/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getBiddingByBidder(@PathVariable long id) {
		
		List<Bidding> biddings = biddingRepository.findAll();
		List<Bidding> byBidder = new ArrayList<>();
		
		for(Bidding b : biddings) {
			if(b.getBidder().getId() == id) {
				byBidder.add(b);
			}
		}
		
		return new ResponseEntity<>(byBidder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getActiveByBidder/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getActiveBiddingByBidder(@PathVariable long id) {
		
		List<Bidding> biddings = biddingRepository.findAll();
		List<Bidding> byBidder = new ArrayList<>();
		
		for(Bidding b : biddings) {
			if(b.getBidder().getId() == id && b.getStatus().equals(BiddingConstants.ACTIVE)) {
				byBidder.add(b);
			}
		}
		
		return new ResponseEntity<>(byBidder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBiddingForGroceries/{gId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getBiddingForGroceries(@PathVariable long gId) {
		
		List<Bidding> all = biddingRepository.findAll();
		List<Bidding> biddings = new ArrayList<>();
		
		for(Bidding b : all) {
			if(b.getGroceries().getId() == gId) {
				biddings.add(b);
			}
		}
		
		return new ResponseEntity<>(biddings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBidItemsByBidding/{bId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getBidItemsByBidding(@PathVariable long bId) {
		
		List<BiddingItem> all = biddingItemRepository.findAll();
		List<BiddingItem> items = new ArrayList<>();
		
		for(BiddingItem bi : all) {
			if(bi.getBidding().getId() == bId) {
				items.add(bi);
			}
		}
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBiddingByGroceriesAndBidder/{gId}/{bId}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getBiddingByGroceriesAndBidder(@PathVariable long gId, @PathVariable long bId) {
		
		List<Bidding> biddings = biddingRepository.findAll();
		
		for(Bidding b : biddings) {
			if(b.getGroceries().getId() == gId && b.getBidder().getId() == bId) {
				return new ResponseEntity<>(b, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
