package e2.isa.grupa5.controller.DTO;

//import e2.isa.grupa5.model.FriendshipRequest;
//import e2.isa.grupa5.service.FriendshipRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
/*
@RequestMapping("/rest/frendship-request")
@RestController
public class FrendshipRequestController {

	@Autowired
	private FriendshipRequestService friendshipRequestService;
	//findByFrom

	@RequestMapping(method = RequestMethod.GET, value= "/find-sent-friendship-requests/{guest-id}")
	public List<FriendshipRequest> findSentFriendshipRequests(@PathVariable(value="guest-id") Long id) {
		return friendshipRequestService.findSentFriendshipRequests(id);
	}

	//Create
	@RequestMapping(method = RequestMethod.GET, value= "/send-friendship-request/{from-id}/{to-id}")
	public FriendshipRequest sendFriendshipRequest(@PathVariable(value="from-id") Long fromId, @PathVariable(value="to-id") Long toId) {
		return friendshipRequestService.sendFriendshipRequest(fromId, toId);
	}
	//Delete
	@RequestMapping(method = RequestMethod.GET, value= "/delete-friendship-request/{friendship-request-id}")
	public ResponseEntity<?> deleteFriendshipRequest(@PathVariable(value="friendship-request-id") Long id) {
		if(friendshipRequestService.deleteFriendshipRequest(id))
			return new ResponseEntity<>(HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//Confirm
	@RequestMapping(method = RequestMethod.GET, value= "/confirm-friendship-request/{friendship-request-id}")
	public ResponseEntity<?> confirmFriendshipRequest(@PathVariable(value="friendship-request-id") Long id) {
		if(friendshipRequestService.confirmFriendshipRequest(id))
			return new ResponseEntity<>(HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}

}
*/
