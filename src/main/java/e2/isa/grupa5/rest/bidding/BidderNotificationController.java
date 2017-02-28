package e2.isa.grupa5.rest.bidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import e2.isa.grupa5.model.bidding.Bidding;
import e2.isa.grupa5.model.bidding.BiddingWSDTO;
import e2.isa.grupa5.model.restaurant.Restaurant;
import e2.isa.grupa5.repository.bidding.BiddingRepository;
import e2.isa.grupa5.repository.restaurant.RestaurantRepository;

@RestController
public class BidderNotificationController {
	
	@Autowired
	private BiddingRepository biddingRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	@MessageMapping("/send")
	public void notifyBidder(@RequestParam BiddingWSDTO message) {
		
		Restaurant r = restaurantRepository.findById(message.getRestaurantId());
		Bidding b = biddingRepository.findById(message.getBiddingId());
		
		String s = "";
		
		if(r != null && b != null) {
			if(message.isStatus()) {
				s = "Ponuda za restoran "+r.getName()+", od: "+ b.getTimestamp().toString() +" je prihvaćena.";
			} else {
				s = "Ponuda za restoran "+r.getName()+", od: "+ b.getTimestamp().toString() +" je odbijena.";
			}
			
			simpMessagingTemplate.convertAndSend("/notifyBidder/"+message.getBidderId(), s);
		}
	}
}
